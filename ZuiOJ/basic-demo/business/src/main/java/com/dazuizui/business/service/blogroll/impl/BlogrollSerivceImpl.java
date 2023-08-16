package com.dazuizui.business.service.blogroll.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.Blogroll;
import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.StatusCodeMessage;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.mapper.BlogrollMapper;
import com.dazuizui.business.service.blogroll.BlogrollSerivce;
import com.dazuizui.business.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
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
    public String getAllBlogroll(@RequestParam("status") Integer status,@RequestParam("delFlag") Integer delFlag) {
        List<Blogroll> allBlogroll = blogrollMapper.getAllBlogroll(status, delFlag);
        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,allBlogroll, StatusCode.OK));
    }

    /**
     * @author Bryan Yang(Dazui) 16/8/2022
     * 插入友情链接
     * insert blogroll
     */
    @Override
    public String inserBlogRoll(Blogroll blogroll){


        String idstring = (String) ThreadLocalUtil.mapThreadLocalOfJWT.get().get("userinfo").get("id");
        blogroll.setCreateBy(Long.valueOf(idstring));
        blogroll.setCreateTime(new Date());

        Long aLong = blogrollMapper.inserBlogRoll(blogroll);

        if (aLong.longValue() == 0){
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
        }

        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,null, StatusCode.OK));
    }

}
