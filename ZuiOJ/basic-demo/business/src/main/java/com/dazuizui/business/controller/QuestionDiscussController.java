package com.dazuizui.business.controller;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.StatusCodeMessage;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.bo.SubmitDiscussBo;
import com.dazuizui.business.service.blog.QuestionDiscussService;
import com.dazuizui.business.util.ThreadLocalUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(value = "评论控制器",tags = {"评论控制器"})
@RestController
@CrossOrigin
@RequestMapping("/discuss")
public class QuestionDiscussController {
    @Autowired
    private QuestionDiscussService discussService;
    /**
     * 提交评论
     * @return
     */
    @ApiOperation("提交评论")
    @PostMapping("/submit")
    public String submitDiscuss(@RequestBody SubmitDiscussBo submitDiscussBo){
        //非空判断
        if (submitDiscussBo.getDiscuss() == null ){
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.IsNull,null,StatusCodeMessage.IsNull));
        }

        //身份验证过期
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        if (map.get("error") != null){
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return discussService.submitDiscuss(submitDiscussBo.getDiscuss());
    }
}
