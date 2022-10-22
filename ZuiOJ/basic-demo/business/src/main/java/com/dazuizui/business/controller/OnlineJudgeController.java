package com.dazuizui.business.controller;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.bo.ProgramBo;
import com.dazuizui.business.service.OnlineJudgeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bryan yang(杨易达)
 * 在线判题系统控制器
 */
@Api(value = "题目判决控制器",tags = {"题目判断控制器"})
@RequestMapping("/onlineJudge")
@RestController
public class OnlineJudgeController {
    @Autowired
    private OnlineJudgeService onlineJudgeService;

    /**
     * 判决代码
     * @param programBo
     * @return
     */
    @ApiOperation("题目判决")
    @PatchMapping("/judge")
    public String judgeTheProgram(@RequestBody ProgramBo programBo){

        return  onlineJudgeService.judgeTheProgram(programBo);
    }
}
