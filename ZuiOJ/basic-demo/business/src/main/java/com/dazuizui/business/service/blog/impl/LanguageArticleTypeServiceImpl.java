package com.dazuizui.business.service.blog.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.StatusCodeMessage;
import com.dazuizui.business.domain.bo.CreateArticleBo;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.mapper.blog.ArticleTypeMapper;
import com.dazuizui.business.mapper.blog.LanguageArticleTypeMapper;
import com.dazuizui.business.service.blog.LanguageArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 语言分类
 */
@Service
public class LanguageArticleTypeServiceImpl implements LanguageArticleTypeService {

    @Autowired
    private LanguageArticleTypeMapper languageArticleTypeMapper;
    @Autowired
    private ArticleTypeMapper articleTypeMapper;

    /**
     * 添加博文在语言分类
     * @return
     */
    @Override
    public String AddArticleType(CreateArticleBo articleBo) {
        //添加语言分类
        Long aLong = languageArticleTypeMapper.AddArticleInLanguageArticle(articleBo);
        if (aLong == 0){
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
        }
        aLong = articleTypeMapper.addArticleType(articleBo);
        if (aLong == 0){
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
        }

        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,null, StatusCode.OK));
    }
}
