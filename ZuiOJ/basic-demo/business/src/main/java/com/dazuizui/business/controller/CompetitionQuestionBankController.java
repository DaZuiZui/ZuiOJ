package com.dazuizui.business.controller;

import com.dazuizui.business.service.onlineJudge.CompetitionQuestionBankService;
import com.dazuizui.business.util.ThreadLocalUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 比赛关联题目controller
 */
@CrossOrigin
@RestController
@RequestMapping("/CompetitionQuestionBank")
public class CompetitionQuestionBankController {

    @Autowired
    private CompetitionQuestionBankService competitionQuestionBankService;

    /**
     * 获取比赛题目id
     * @param token
     * @param contestId
     * @return
     */
    @ApiOperation("获取比赛题目")
    @GetMapping("/getQuestionListInContest")
    public String getQuestionListInContest(@RequestParam("token")String token, @RequestParam("contestId")Long contestId){
        return competitionQuestionBankService.getQuestionListInContest(contestId);
    }
}
