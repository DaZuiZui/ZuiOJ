package com.dazuizui.business.service.blog.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.StatusCodeMessage;
import com.dazuizui.basicapi.entry.bo.CreateArticleBo;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.mapper.blog.LanguageArticleTypeMapper;
import com.dazuizui.business.service.blog.LanguageArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageArticleTypeServiceImpl implements LanguageArticleTypeService {

    @Autowired
    private LanguageArticleTypeMapper languageArticleTypeMapper;

    /**
     * 添加博文在语言分类
     * @return
     */
    @Override
    public String AddArticleInLanguageArticle(CreateArticleBo articleBo) {
        Long aLong = languageArticleTypeMapper.AddArticleInLanguageArticle(articleBo);

        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,null, StatusCode.OK));
    }
}
