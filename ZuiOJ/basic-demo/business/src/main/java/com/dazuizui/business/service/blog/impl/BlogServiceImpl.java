package com.dazuizui.business.service.blog.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dazuizui.basicapi.entry.*;
import com.dazuizui.basicapi.entry.bo.CreateArticleBo;
import com.dazuizui.basicapi.entry.bo.GetBlogPostsByPageBo;
import com.dazuizui.basicapi.entry.bo.GetQuestionAnswerByPageBo;
import com.dazuizui.basicapi.entry.vo.ArticleVo;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.mapper.*;
import com.dazuizui.business.messageQueue.blog.config.BlogSource;
import com.dazuizui.business.service.blog.BlogService;
import com.dazuizui.business.util.RedisUtil;
import com.dazuizui.business.util.ThreadLocalUtil;
import com.dazuizui.business.util.TransactionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

/**
 * 博文业务实现接口
 */
@Service
@EnableBinding(BlogSource.class)
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private BlogSource source;
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
            aLong = articleAttributeMapper.increaseTheNumberOfTable(articleBo.getPrivacy(),1L,1);
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

        //写入博文数量
        aLong = attributeMapper.IncreaseTheNumberOfTable(AttributeKey.article,1L);
        if (aLong == 0){
            transactionUtils.rollback(transactionStatus);
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
        }

        //todo 根据分类添加数量 如根据语言分类的数量

        /**
         * 写入redis
         */
        redisUtil.setStringInRedis(RedisKey.ZuiBlogArticle+articleBo.getId(),RedisKey.OutTime,articleBo);
        //添加总题解数量
        redisUtil.increment(RedisKey.ZuiOJNumberOfQustionAnswer,RedisKey.OutTime,1);
        //添加总题解指定状态的数量
        redisUtil.increment(RedisKey.ZuiOJQuestionAnswerPrivicy+articleBo.getPrivacy(),RedisKey.OutTime,1);

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
            aLong = attributeMapper.IncreaseTheNumberOfTable(AttributeKey.article, 1L);
            if (aLong == 0){
                transactionUtils.rollback(transactionStatus);
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            }

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
            aLong = attributeMapper.IncreaseTheNumberOfTable(AttributeKey.question_answer,1L);
            if (aLong == 0){
                transactionUtils.rollback(transactionStatus);
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            }

            //添加博客板块所有博文详细数量
            aLong = articleAttributeMapper.increaseTheNumberOfTable(articleBo.getPrivacy(),1L,1);
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
        //添加总题解数量
        redisUtil.increment(RedisKey.ZuiOJNumberOfQustionAnswer,RedisKey.OutTime,1);
        //添加总题解指定状态的数量
        redisUtil.increment(RedisKey.ZuiOJQuestionAnswerPrivicy+articleBo.getPrivacy(),RedisKey.OutTime,1);
        //添加当前题解的数量
        redisUtil.increment(RedisKey.ZuiOJQuestionAnswerOf+articleBo.getId(),RedisKey.OutTime,1);
        //添加当前题的题解状态数量
        redisUtil.increment(RedisKey.ZuiOJQuestionAnswerPrivicyOf+articleBo.getPrivacy()+":"+articleBo.getId(),RedisKey.OutTime,1);

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
        Long count = articleAttributeMapper.queryCountByStatus(getBlogPostsByPageBo.getStatus());
        //分页获取数据
        List<ArticleJSON> articleByPage = blogMapper.getArticleByPage(getBlogPostsByPageBo);
        List<ArticleVo> res = new ArrayList<>();
        System.out.println(articleByPage);
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
}
