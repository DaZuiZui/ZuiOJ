package com.dazuizui.business.service.blog.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.bo.CreateArticleBo;
import com.dazuizui.business.mapper.BlogMapper;
import com.dazuizui.business.service.blog.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 博文业务实现接口
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;

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
        blogMapper.insertBlogMdText(articleBo);
        articleBo.setCreateTime(new Date());
        //写入简介文档
        blogMapper.insertBlog(
                articleBo,
                JSONArray.toJSONString(articleBo.getArticleTypeArray()),
                JSONArray.toJSONString(articleBo.getLanguageTypeArray()));

        /**
         * todo写入redis
         */

        /**
         * todo消息队列，处理分类内容，和个人文件夹
         */

        return null;
    }
}
