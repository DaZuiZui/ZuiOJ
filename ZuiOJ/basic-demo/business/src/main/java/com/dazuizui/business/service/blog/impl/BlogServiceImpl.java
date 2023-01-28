package com.dazuizui.business.service.blog.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dazuizui.basicapi.entry.*;
import com.dazuizui.basicapi.entry.bo.CreateArticleBo;
import com.dazuizui.basicapi.entry.bo.GetQuestionAnswerByPageBo;
import com.dazuizui.basicapi.entry.vo.ArticleVo;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.mapper.AttributeMapper;
import com.dazuizui.business.mapper.BlogMapper;
import com.dazuizui.business.mapper.QuestionAnswerAttributeMapper;
import com.dazuizui.business.mapper.QuestionAnswerMapper;
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
    
    /**
     * 创建博文
     * @param articleBo
     * @return
     */
    @Override
    @Transactional
    public String createArticle(CreateArticleBo articleBo) {
        /**
         * 写入mysql
         */
        //写入MD文档
        Long aLong = blogMapper.insertBlogMdText(articleBo);
        if (aLong == 0){
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
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
        }

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
     * @param articleBo
     * @return
     */
    @Override
    public String createQuestionAnswer(CreateArticleBo articleBo,Long questionId) {
        TransactionStatus transactionStatus = transactionUtils.begin(TransactionDefinition.ISOLATION_READ_COMMITTED);

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

            //写入题解文档
            aLong = questionAnswerMapper.addQuestionAnswer(articleBo,questionId);
            if (aLong == 0){
                transactionUtils.rollback(transactionStatus);
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            }
            //增加题解数量
            aLong = questionAnswerAttributeMapper.increaseTheNumberOfSolutions(questionId,articleBo.getPrivacy(),1, 1L);
            if (aLong == 0){
                transactionUtils.rollback(transactionStatus);
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            }

            aLong = attributeMapper.IncreaseTheNumberOfTable(AttributeKey.question_answer,1L);
            System.err.println(aLong);
            if (aLong == 0){
                transactionUtils.rollback(transactionStatus);
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            }
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
        //增加当前题解数量
        redisUtil.increment(RedisKey.ZuiOJNumberOfQustionAnswer,RedisKey.OutTime,1);
        redisUtil.increment(RedisKey.ZuiOJQuestionAnswerPrivicy+articleBo.getPrivacy(),RedisKey.OutTime,1);

        /**
         * 消息队列，处理分类内容，和个人文件夹的分类管理
         */
        source.addArticleOutput().send(MessageBuilder.withPayload(articleBo).build());

        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,null, StatusCode.OK));
    }

    /**
     * 分页获取题解数据
     * @param getQuestionAnswerByPageBo
     * @return
     */
    @Override
    public String getQuestionAnswerByPage(GetQuestionAnswerByPageBo getQuestionAnswerByPageBo){
        //获取指定状态数据总数量
        Long count = questionAnswerAttributeMapper.queryNumberOfQuestionByStatus(getQuestionAnswerByPageBo.getQuestionId(),getQuestionAnswerByPageBo.getStatus());
        //分页获取数据
        List<ArticleJSON> questionAnswerByPage = blogMapper.getQuestionAnswerByPage(getQuestionAnswerByPageBo);
        System.out.println("\n\n\n"+questionAnswerByPage.get(0));
        List<ArticleVo> res = new ArrayList<>();

        //将JSON转换为List
        for (ArticleJSON articleJSON : questionAnswerByPage) {
            ArticleVo article  =new ArticleVo();
            article.setId(articleJSON.getId());
            article.setTitle(articleJSON.getTitle());
            article.setIntroduce(articleJSON.getIntroduce());
            List<Integer> articleType    = (List<Integer>) JSONObject.parseObject(articleJSON.getArticleType(),Object.class);
            article.setArticleType(articleType);
            //List<Integer> technologyType = (List<Integer>) JSONObject.parseObject(articleJSON.getTechnologyType(),Object.class);
            article.setTechnologyType(article.getTechnologyType());
            List<Integer> language = (List<Integer>) JSONObject.parseObject(articleJSON.getLanguage(),Object.class);
            article.setLanguage(language);
            article.setCreateTime(articleJSON.getCreateTime());
            article.setCreateBy(articleJSON.getCreateBy());
            article.setCreateByName(articleJSON.getCreateByName());
            article.setMdTextId(articleJSON.getMdTextId());
            res.add(article);
        }



        //封装返回数据
        Map<String,Object> map = new HashMap<>();
        map.put("count",count);
        map.put("data",res);

        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,map, StatusCode.OK));
    }
}
