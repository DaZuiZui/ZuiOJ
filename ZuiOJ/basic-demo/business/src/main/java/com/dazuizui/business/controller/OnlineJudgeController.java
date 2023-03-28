package com.dazuizui.business.controller;

import com.dazuizui.basicapi.entry.bo.ProgramBo;
import com.dazuizui.business.service.onlineJudge.OnlineJudgeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Bryan yang(杨易达)
 * 在线判题系统控制器
 */
@CrossOrigin
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
    @PostMapping("/judge")
    public String judgeTheProgram(@RequestParam("token")String token,@RequestBody ProgramBo programBo){
        return  onlineJudgeService.judgeTheProgram(programBo);
    }
}
