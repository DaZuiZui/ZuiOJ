package com.dazuizui.business.controller;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.StatusCodeMessage;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.service.blogroll.BlogrollSerivce;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 友情链接控制器
 */
@CrossOrigin
@RequestMapping("/blogroll")
@RestController
@Api(value = "友情链接控制器",tags = {"友情链接控制器"})
public class BlogrollController {

    @Autowired
    private BlogrollSerivce blogrollSerivce;

    /**
     * 获取全部的连接
     * @param status    状态
     * @param delFlag   逻辑删除
     * @return
     */
    @GetMapping("/getAllBlogroll")
    @ApiOperation("获取友情链接")
    public String getAllBlogroll(@RequestParam("status") Integer status,@RequestParam("delFlag") Integer delFlag){
        if (status == null || delFlag == null){
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.IsNull,null, StatusCode.IsNull));
        }

        return blogrollSerivce.getAllBlogroll(status,delFlag);
    }
}
