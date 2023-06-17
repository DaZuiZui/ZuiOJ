package com.dazuizui.business.service.blog.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dazuizui.basicapi.entry.*;
import com.dazuizui.business.domain.Article;
import com.dazuizui.business.domain.BlogAttribute;
import com.dazuizui.business.domain.QuestionAnswer;
import com.dazuizui.business.domain.RedisKey;
import com.dazuizui.business.domain.bo.*;
import com.dazuizui.basicapi.entry.bo.GetArticleByIdBo;
import com.dazuizui.basicapi.entry.bo.GetBlogPostsByPageBo;
import com.dazuizui.business.domain.vo.AdminGetArticleByPaginVo;
import com.dazuizui.business.domain.vo.ArticleVo;
import com.dazuizui.basicapi.entry.vo.QuestionBankVo;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.vo.UserGetMyselfArticleVo;
import com.dazuizui.business.mapper.*;
import com.dazuizui.business.messageQueue.cofnig.MessageSource;
import com.dazuizui.business.service.blog.BlogService;
import com.dazuizui.business.service.onlineJudge.QuestionAnswerService;
import com.dazuizui.business.service.user.UserService;
import com.dazuizui.business.util.RedisUtil;
import com.dazuizui.business.util.ThreadLocalUtil;
import com.dazuizui.business.util.TransactionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

/**
 * 博文业务实现接口
 */
@Service
@EnableBinding(MessageSource.class)
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private MessageSource source;
    @Autowired
    private QuestionAnswerMapper questionAnswerMapper;
    @Autowired
    private QuestionAnswerAttributeMapper questionAnswerAttributeMapper;
    @Autowired
    private AttributeMapper attributeMapper;
    @Autowired
    private TransactionUtils transactionUtils;
    @Autowired
    private ArticleAttributeMapper articleAttributeMapper;
    @Autowired
    private UserArticleAttributeMapper userArticleAttributeMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private BlogAttributeMapper blogAttributeMapper;
    @Autowired
    private QuestionAnswerService questionAnswerService;

    /**
     * 创建博文
     * @param articleBo
     * @return
     */
    @Override
    public String createArticle(CreateArticleBo articleBo) {
        TransactionStatus transactionStatus = transactionUtils.begin(TransactionDefinition.ISOLATION_READ_COMMITTED);

        /**
         * 写入mysql
         */
        Long aLong = null;
        try {
            //写入MD文档
            aLong = blogMapper.insertBlogMdText(articleBo);
            if (aLong == 0){
                transactionUtils.rollback(transactionStatus);
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            }
            articleBo.setMdTextId(articleBo.getId());
            articleBo.setCreateTime(new Date());
            articleBo.setCreateBy(Long.valueOf(ThreadLocalUtil.mapThreadLocalOfJWT.get().get("userinfo").get("id")+""));

            //写入简介文档
            aLong = blogMapper.insertBlog(
                    articleBo,
                    JSONArray.toJSONString(articleBo.getArticleTypeArray()),
                    JSONArray.toJSONString(articleBo.getLanguageTypeArray()));
            if (aLong == 0){
                transactionUtils.rollback(transactionStatus);
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            }

            //添加博客板块所有博文详细数量
            aLong =  blogAttributeMapper.articleQuantitManagement(articleBo.getPrivacy(),1l);
            if (aLong == 0){
                transactionUtils.rollback(transactionStatus);
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            }

            //添加用户的博文数量
            Long userId = Long.valueOf(ThreadLocalUtil.mapThreadLocalOfJWT.get().get("userinfo").get("id")+"");
            aLong = userArticleAttributeMapper.increaseTheNumberOfUserActicle(articleBo.getPrivacy(),userId,1L,1);
            if (aLong == 0){
                transactionUtils.rollback(transactionStatus);
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            transactionUtils.rollback(transactionStatus);
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
        }
        transactionUtils.commit(transactionStatus);

        /**
         * 写入redis
         */
        redisUtil.setStringInRedis(RedisKey.ZuiBlogArticle+articleBo.getId(),RedisKey.OutTime,articleBo);


        /**
         * 消息队列，处理分类内容，和个人文件夹的分类管理
         */
        source.addArticleOutput().send(MessageBuilder.withPayload(articleBo).build());

        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,null, StatusCode.OK));
    }

    /**
     * 创建题解贡献
     * todo 事物
     * @param articleBo
     * @return
     */
    @Override
    public String createQuestionAnswer(CreateArticleBo articleBo,Long questionId) {
        TransactionStatus transactionStatus = transactionUtils.begin(TransactionDefinition.ISOLATION_READ_COMMITTED);
        articleBo.setCreateBy(Long.valueOf( ThreadLocalUtil.mapThreadLocalOfJWT.get().get("userinfo").get("id")+""));
        /**
         * 写入mysql
         */
        try {
            //写入MD文档
            Long aLong = blogMapper.insertBlogMdText(articleBo);
            if (aLong == 0){
                transactionUtils.rollback(transactionStatus);
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            }
            articleBo.setMdTextId(articleBo.getId());
            articleBo.setCreateTime(new Date());
            articleBo.setCreateBy(Long.valueOf(ThreadLocalUtil.mapThreadLocalOfJWT.get().get("userinfo").get("id")+""));

            //写入简介文档
            aLong = blogMapper.insertBlog(
                    articleBo,
                    JSONArray.toJSONString(articleBo.getArticleTypeArray()),
                    JSONArray.toJSONString(articleBo.getLanguageTypeArray()));
            if (aLong == 0){
                transactionUtils.rollback(transactionStatus);
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            }

            //写入博文数量
            //aLong = attributeMapper.increaseTheNumberOfTable(AttributeKey.article, 1L);
            // if (aLong == 0){
            //    transactionUtils.rollback(transactionStatus);
            //    return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            //}

            //建立题解与博文的关联
            aLong = questionAnswerMapper.addQuestionAnswer(articleBo,questionId,Long.valueOf(ThreadLocalUtil.mapThreadLocalOfJWT.get().get("userinfo").get("id")+""),articleBo.getCreateTime());
            if (aLong == 0){
                transactionUtils.rollback(transactionStatus);
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            }

            //增加该题题解详细数量
            aLong = questionAnswerAttributeMapper.increaseTheNumberOfSolutions(questionId,articleBo.getPrivacy(),1, 1L);
            if (aLong == 0){
                transactionUtils.rollback(transactionStatus);
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            }

            //全站题解数量
            //aLong = attributeMapper.increaseTheNumberOfTable(AttributeKey.question_answer,1L);
            //if (aLong == 0){
            //    transactionUtils.rollback(transactionStatus);
              //  return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            //}

            //添加博客板块所有博文详细数量
            aLong =  blogAttributeMapper.articleQuantitManagement(articleBo.getPrivacy(),1l);
            if (aLong == 0){
                transactionUtils.rollback(transactionStatus);
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            }

            //添加用户的博文数量
            Long userId = Long.valueOf(ThreadLocalUtil.mapThreadLocalOfJWT.get().get("userinfo").get("id")+"");
            aLong = userArticleAttributeMapper.increaseTheNumberOfUserActicle(articleBo.getPrivacy(),userId,1L,1);
            if (aLong == 0){
                transactionUtils.rollback(transactionStatus);
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            }

            //todo 根据分类添加数量 如根据语言分类等

        } catch (Exception e) {
            e.printStackTrace();
            transactionUtils.rollback(transactionStatus);
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
        }
        //提交
        transactionUtils.commit(transactionStatus);

        /**
         * 写入redis
         */
        //设置Redis缓存
        redisUtil.setStringInRedis(RedisKey.ZuiBlogArticle+articleBo.getId(),RedisKey.OutTime,articleBo);

        /**
         * 消息队列，处理分类内容，和个人文件夹的分类管理
         */
        source.addArticleOutput().send(MessageBuilder.withPayload(articleBo).build());

        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,null, StatusCode.OK));
    }

    /**
     * 分页获取博文数据
     * @param getBlogPostsByPageBo
     * @return
     */
    @Override
    public String getBlogPostsByPage(@RequestBody GetBlogPostsByPageBo getBlogPostsByPageBo){
        //获取博文个数
        Long count = blogAttributeMapper.queryNumberOfPublicArticles();
        //分页获取数据
        List<ArticleJSON> articleByPage = blogMapper.getArticleByPage(getBlogPostsByPageBo);
        List<ArticleVo> res = new ArrayList<>();

        //将JSON转换为List
        for (ArticleJSON articleJSON : articleByPage) {
            ArticleVo article  =new ArticleVo();
            article.setId(articleJSON.getId());
            article.setTitle(articleJSON.getTitle());
            article.setIntroduce(articleJSON.getIntroduce());
            List<Integer> articleType    = (List<Integer>) JSONObject.parseObject(articleJSON.getArticleType(),Object.class);
            article.setArticleType(articleType);
            //List<Integer> technologyType = (List<Integer>) JSONObject.parseObject(articleJSON.getTechnologyType(),Object.class);
            article.setTechnologyType(articleJSON.getTechnologyType());
            List<Integer> language = (List<Integer>) JSONObject.parseObject(articleJSON.getLanguage(),Object.class);
            article.setLanguage(language);
            article.setCreateTime(articleJSON.getCreateTime());
            article.setCreateBy(articleJSON.getCreateBy());
            article.setCreateByName(articleJSON.getCreateByName());
            article.setMdTextId(articleJSON.getMdTextId());
            article.setLikes(articleJSON.getLikes());

            res.add(article);
        }

        //封装返回数据
        Map<String,Object> map = new HashMap<>();
        map.put("count",count);
        map.put("data",res);
        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,map, StatusCode.OK));
    }

    /**
     * 获取指定文章的数据数量
     * @param status
     * @return
     */
    @Override
    public Long queryCountByStatus(Integer status) {
        return null;
    }

    /**
     * 根据id获取博文
     * @param getArticleByIdBo
     * @return
     */
    @Override
    public String getArticleById(GetArticleByIdBo getArticleByIdBo) {
        //从redis获取数据
        CreateArticleBo detailedArticleBo = (CreateArticleBo) redisUtil.getStringInRedis(RedisKey.ZuiBlogArticle+getArticleByIdBo.getId());
        if (detailedArticleBo == null){
            detailedArticleBo = new CreateArticleBo();
            //从数据库获取数据
            DetailedArticleJSON articleById = blogMapper.getArticleById(getArticleByIdBo);
            //判断这条数据是否不存在
            if (articleById == null){
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.DateIsNull,null, StatusCode.DateIsNull));
            }

            //转为集合数据
            detailedArticleBo.setId(articleById.getId());
            detailedArticleBo.setTitle(articleById.getTitle());
            List<Integer> articleType    = (List<Integer>) JSONObject.parseObject(articleById.getArticleType(),Object.class);
            detailedArticleBo.setArticleTypeArray(articleType);
            List<Integer> languageType = (List<Integer>) JSONObject.parseObject(articleById.getLanguage(),Object.class);
            detailedArticleBo.setLanguageTypeArray(languageType);
            detailedArticleBo.setTechnologyType(articleById.getTechnologyType());
            detailedArticleBo.setMdTextId(articleById.getId());
            detailedArticleBo.setCreateBy(articleById.getCreateBy());
            detailedArticleBo.setCreateTime(articleById.getCreateTime());
            detailedArticleBo.setLikes(articleById.getLikes());
            detailedArticleBo.setMdText(articleById.getMdText());
            detailedArticleBo.setPrivacy(articleById.getPrivacy());
            //存入redis
            redisUtil.setStringInRedis(RedisKey.ZuiBlogArticle+detailedArticleBo.getId(),RedisKey.OutTime,detailedArticleBo);
        }

        //判断是否有查看权限
        if (detailedArticleBo.getPrivacy() != 0){
            //查看是否没有登入，如果没有登入无权访问
            Boolean userauth = (Boolean) ThreadLocalUtil.DataOfThreadLocal.get().get("userauth");
            if (userauth != null && userauth == false){
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.NotAuthorizedToView,detailedArticleBo, StatusCode.NotAuthorizedToView));
            }

            //查看是否不是本人查看自己发布的出现问题的博文
            Long userId = Long.valueOf(ThreadLocalUtil.mapThreadLocalOfJWT.get().get("userinfo").get("id")+"");
            if (userId != detailedArticleBo.getCreateBy()){
                //检测是否为管理员
                User user = userService.queryUserById(userId);
                if (user.getRole() <= 2) {
                    return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.NotAuthorizedToView,null, StatusCode.NotAuthorizedToView));
                }
            }
        }

        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,detailedArticleBo, StatusCode.OK));
    }

    @Autowired
    private QuestionBankMapper questionBankMapper;
    /**
     * 检查是否为比赛题目
     * @param id
     * @return
     */
    @Override
    public String checkIfTheTopicIsACompetitionTopic(Long id) {
        //去redis检查
        QuestionBankVo questionBankVo = (QuestionBankVo) redisUtil.getStringInRedis(RedisKey.ZuiOJQuestion+id);
        if (questionBankVo == null){
            questionBankVo = questionBankMapper.getQuestionById(id,1,0);
            if (questionBankVo == null){
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,true, StatusCode.OK));
            }else{
                //System.out.println(questionBankVo);
                redisUtil.setStringInRedis(RedisKey.ZuiOJQuestion+id,RedisKey.OutTime,questionBankVo);
            }
        }

        if (questionBankVo.getStatus() ==1) {
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,true, StatusCode.OK));
        }

        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,false, StatusCode.OK));
    }

    /**
     * 管理员分页获取博文
     * @param adminGetArticleByPaginBo
     * @return
     */
    @Override
    public String adminGetArticleByPagin(AdminGetArticleByPaginBo adminGetArticleByPaginBo) {
        //获取博文数量
        BlogAttribute blogAttribute = blogAttributeMapper.queryAttribute();
        //分页查询
        List<Article> articles = blogMapper.adminGetArticleByPagin(adminGetArticleByPaginBo);
        //封装返回数据
        AdminGetArticleByPaginVo article = new AdminGetArticleByPaginVo();
        article.setArticleList(articles);
        if (adminGetArticleByPaginBo.getStatus() == 0){
            article.setCount(blogAttribute.getPublicArticle());
        }else if (adminGetArticleByPaginBo.getStatus() == 1){
            article.setCount(blogAttribute.getPrivateArticle());
        }else if (adminGetArticleByPaginBo.getStatus() == 2){
            article.setCount(blogAttribute.getBannedArticle());
        }
        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,article, StatusCode.OK));
    }

    /**
     * 管理员逻辑删除博文通过Id
     * @return
     */
    @Override
    public String adminDeleteAritcleById(AdminDeleteAritcleByIdBo adminDeleteAritcleByIdBo) {
        //逻辑删除状态
        TransactionStatus begin = transactionUtils.begin(TransactionDefinition.ISOLATION_READ_COMMITTED);
        try {
            //标记删除
            Long numberOfOperations = blogMapper.adminDeleteAritcleById(adminDeleteAritcleByIdBo);
            if (numberOfOperations == 0){
                transactionUtils.rollback(begin);
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            }
            //博文数据表指定数量减少
            numberOfOperations = blogAttributeMapper.articleQuantitManagement(adminDeleteAritcleByIdBo.getStatus(),-1l);
            if (numberOfOperations == 0){
                transactionUtils.rollback(begin);
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            }

            /**
             * 被删除封禁的文章+1
             */
            numberOfOperations = blogAttributeMapper.articleQuantitManagement(2,1l);
            if (numberOfOperations == 0){
                transactionUtils.rollback(begin);
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            }

            //查看该博文是否为题解，如果是题解类型就指定题解数量减少
            QuestionAnswer byArticleId = questionAnswerService.findByArticleId(adminDeleteAritcleByIdBo.getId());
            if (byArticleId != null){
                //删除题解数量
                numberOfOperations = questionAnswerAttributeMapper.increaseTheNumberOfSolutions(byArticleId.getQuestionId(),0,0,-1l);
                if (numberOfOperations == 0){
                    transactionUtils.rollback(begin);
                    return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
                }
            }

            //删除redis缓存
            redisUtil.deleteKey(RedisKey.ZuiBlogArticle+adminDeleteAritcleByIdBo.getId());
        } catch (Exception e) {
            transactionUtils.rollback(begin);
            e.printStackTrace();
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
        }
        transactionUtils.commit(begin);

        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,null, StatusCode.OK));
    }


    @Override
    public List<ArticleVo> ArticleJSONtoList(List<ArticleJSON> articleByPage) {
        List<ArticleVo> res = new ArrayList<>();

        //将JSON转换为List
        for (ArticleJSON articleJSON : articleByPage) {
            ArticleVo article  =new ArticleVo();
            article.setId(articleJSON.getId());
            article.setTitle(articleJSON.getTitle());
            article.setIntroduce(articleJSON.getIntroduce());
            List<Integer> articleType    = (List<Integer>) JSONObject.parseObject(articleJSON.getArticleType(),Object.class);
            article.setArticleType(articleType);
            //List<Integer> technologyType = (List<Integer>) JSONObject.parseObject(articleJSON.getTechnologyType(),Object.class);
            article.setTechnologyType(articleJSON.getTechnologyType());
            List<Integer> language = (List<Integer>) JSONObject.parseObject(articleJSON.getLanguage(),Object.class);
            article.setLanguage(language);
            article.setCreateTime(articleJSON.getCreateTime());
            article.setCreateBy(articleJSON.getCreateBy());
            article.setCreateByName(articleJSON.getCreateByName());
            article.setMdTextId(articleJSON.getMdTextId());
            article.setLikes(articleJSON.getLikes());

            res.add(article);
        }

        return res;
    }

    @Override
    public List<ArticleJSON> queryAllTopArticle() {
        return null;
    }

    /**
     * 管理员物理删除博文
     * @param physicallyDeleteArticlesBo
     * @return
     */
    @Override
    public String physicallyDeleteArticles(PhysicallyDeleteArticlesBo physicallyDeleteArticlesBo) {
        TransactionStatus begin = transactionUtils.begin(TransactionDefinition.ISOLATION_READ_COMMITTED);

        try {
            //获取要删除的Mdtext文档
            List<Long> mdTextIList = blogMapper.queryMdTextIdOfDeleteAritcle(physicallyDeleteArticlesBo.getElements());
            //批量删除文章
            Long numberOfDeleteAritcleOptions = blogMapper.physicallyDeleteArticles(physicallyDeleteArticlesBo.getElements());
            //批量删除Mdtext文档
            Long numberOfDeleteArticleOptions = blogMapper.physicallyDeleteArticleMdText(mdTextIList);
            //删除分类
            Long numberOfDeleteArticleOptionsInType = blogMapper.physicallyDeleteArticleInType(physicallyDeleteArticlesBo.getElements());
            //删除题解体
            Long numberOfDeleteQuestionAnswerOptions = questionAnswerMapper.deleteQuestionAnswer(physicallyDeleteArticlesBo.getElements());
        } catch (Exception e) {
            transactionUtils.rollback(begin);
            e.printStackTrace();
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
        }

        transactionUtils.commit(begin);
        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,null, StatusCode.OK));
    }

    /**
     * 获取用户发布的文章
     * @param userGetMyselfArticleBo
     * @return
     */
    @Override
    public String userGetMyselfArticle(UserGetMyselfArticleBo userGetMyselfArticleBo) {
        //用户id
        Long userId = Long.valueOf(ThreadLocalUtil.mapThreadLocalOfJWT.get().get("userinfo").get("id")+"");
        userGetMyselfArticleBo.setId(userId);
        //分页查询数据
        List<ArticleJSON> articleJSONS = blogMapper.userGetMyselfArticle(userGetMyselfArticleBo);
        //查询数据数量
        List<ArticleVo> articleVos = this.ArticleJSONtoList(articleJSONS);
        //查询用户发布的数据个数
        Long countOfArticleOfUser = blogMapper.getCountOfArticleOfUser(userGetMyselfArticleBo);
        UserGetMyselfArticleVo userGetMyselfArticleVo = new UserGetMyselfArticleVo(countOfArticleOfUser,articleVos);

        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,userGetMyselfArticleVo, StatusCode.OK));
    }


}
