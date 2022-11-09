package com.dazuizui.business.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * 比赛关联题目controller
 */
@CrossOrigin
@RestController
@RequestMapping("/CompetitionQuestionBank")
public class CompetitionQuestionBankController {
    /**
     * 获取比赛题目id
     * @param token
     * @param contestId
     * @return
     */
    @ApiOperation("获取比赛题目")
    @GetMapping("/getQuestionListInContest")
    public String getQuestionListInContest(@RequestParam("token")String token, @RequestParam("contestId")Long contestId){
        return "";
    }
}
