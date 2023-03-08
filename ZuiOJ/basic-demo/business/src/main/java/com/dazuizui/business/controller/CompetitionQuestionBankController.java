package com.dazuizui.business.controller;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
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
        //报错排查
        if (ThreadLocalUtil.mapThreadLocal.get().get("error") != null) {
            return JSONArray.toJSONString(new ResponseVo<>(ThreadLocalUtil.mapThreadLocal.get().get("error"),null,ThreadLocalUtil.mapThreadLocal.get().get("code")));
        }

        return competitionQuestionBankService.getQuestionListInContest(contestId);
    }

    /**
     * 获取比赛题目id
     * @param token
     * @param contestId
     * @return
     */
    @ApiOperation("获取比赛题目")
    @GetMapping("/admin/getQuestionListInContest")
    public String adminGetQuestionListInContest(@RequestParam("token")String token, @RequestParam("contestId")Long contestId){
        //身份验证过期
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();

        if (map.get("error") != null){
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return competitionQuestionBankService.getQuestionListInContest(contestId);
    }
}
