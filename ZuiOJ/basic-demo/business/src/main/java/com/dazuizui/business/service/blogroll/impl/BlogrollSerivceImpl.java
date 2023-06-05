package com.dazuizui.business.service.blogroll.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.Blogroll;
import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.StatusCodeMessage;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.mapper.BlogrollMapper;
import com.dazuizui.business.service.blogroll.BlogrollSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 获取友情链接
 */
@Service
public class BlogrollSerivceImpl implements BlogrollSerivce {
    @Autowired
    private BlogrollMapper blogrollMapper;


    /**
     * 获取全部的连接
     * @param status
     * @param delFlag
     * @return
     */
    @Override
    public String getAllBlogroll(Integer status, Integer delFlag) {
        List<Blogroll> allBlogroll = blogrollMapper.getAllBlogroll(status, delFlag);
        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,allBlogroll, StatusCode.OK));
    }
}
