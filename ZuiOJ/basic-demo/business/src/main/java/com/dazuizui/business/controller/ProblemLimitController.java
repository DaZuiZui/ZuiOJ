package com.dazuizui.business.controller;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.ProblemLimit;
import com.dazuizui.basicapi.entry.StatusCodeMessage;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.service.onlineJudge.ProblemLimitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Api(value = "问题时间限制控制器",tags = {"问题时间限制控制器"})
@RequestMapping("/problemlimit")
@RestController
public class ProblemLimitController {

    @Autowired
    private ProblemLimitService problemLimitService;

    /**
     * 增加问题限制
     * @param problemLimit
     * @return
     */
    @ApiOperation("添加题目限制")
    @PostMapping("/addProblemLimit")
    public String addProblemLimit(@RequestParam("token")String token,@RequestBody ProblemLimit problemLimit){
        //非空判断
        if (problemLimit == null){
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.IsNull,null,StatusCodeMessage.IsNull));
        }
       return problemLimitService.addProblemLimit(problemLimit);
    }
}
