package com.dazuizui.business.controller;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.Blogroll;
import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.StatusCodeMessage;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.bo.DeleteBlogRollBo;
import com.dazuizui.business.service.blogroll.BlogrollSerivce;
import com.dazuizui.business.util.ThreadLocalUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    /**
     * @author Bryan Yang(Dazui) 16/8/2022
     * 插入友情链接
     * insert blogroll
     */
    @PostMapping("/inserBlogRoll")
    @ApiOperation("插入友情链接")
    public String applyForBlogRoll(@RequestParam("nonPowerToken") String nonPowerToken,@RequestParam("token")String token,@RequestBody Blogroll blogroll){
        if (blogroll == null){
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.IsNull,null, StatusCode.IsNull));
        }

        //身份验证过期或者权限不足的校验
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        ThreadLocalUtil.mapThreadLocal.remove();

        if (map.get("error") != null){
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return blogrollSerivce.inserBlogRoll(blogroll);
    }

    /**
     * @auhtor Bryan Yang(Dazui)
     * 逻辑删除友情链接
     * Tombstone link
     */
    @PostMapping("/tombstoneLink")
    @ApiOperation("逻辑删除友情链接")
    public String tombstoneLink(@RequestBody DeleteBlogRollBo deleteBlogRollBo){
        if (deleteBlogRollBo == null){
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.IsNull,null, StatusCode.IsNull));
        }

        //身份验证过期或者权限不足的校验
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        ThreadLocalUtil.mapThreadLocal.remove();

        if (map.get("error") != null){
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return blogrollSerivce.tombstoneLink(deleteBlogRollBo);
    }

    /**
     * @author Bryan yang(Dazui)
     * 物理删除友情链接
     * Delete BlogRoll
     */
    @PostMapping("/deleteBlogRoll")
    @ApiOperation("物理删除友情链接")
    public String deleteBlogRoll(@RequestBody DeleteBlogRollBo deleteBlogRollBo){
        if (deleteBlogRollBo == null){
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.IsNull,null, StatusCode.IsNull));
        }

        //身份验证过期或者权限不足的校验
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        ThreadLocalUtil.mapThreadLocal.remove();

        if (map.get("error") != null){
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return blogrollSerivce.deleteBlogRoll(deleteBlogRollBo);
    }
}
