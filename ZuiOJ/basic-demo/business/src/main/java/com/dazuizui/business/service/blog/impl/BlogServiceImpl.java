package com.dazuizui.business.service.blog.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.RedisKey;
import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.StatusCodeMessage;
import com.dazuizui.basicapi.entry.bo.CreateArticleBo;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.mapper.BlogMapper;
import com.dazuizui.business.messageQueue.blog.config.BlogSource;
import com.dazuizui.business.service.blog.BlogService;
import com.dazuizui.business.util.RedisUtil;
import com.dazuizui.business.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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
}
