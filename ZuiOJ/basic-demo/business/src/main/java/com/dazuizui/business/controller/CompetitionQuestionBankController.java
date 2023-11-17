package com.dazuizui.business.controller;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.CompetitionQuestionBank;
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
     * 删除比赛关联题目
     * todo aop鉴权
     * @param questionId
     * @param contestId
     * @return
     */
    @ApiOperation("移除比赛关联题目")
    @PostMapping("/removeQuestion")
    public String removeQuestion(@RequestParam("questionId") Long questionId,@RequestParam("contestId") Long contestId){
        return competitionQuestionBankService.removeQuestion(questionId,contestId);
    }

    /**
     * 添加比赛关联题目
     * @return
     */
    @ApiOperation("添加比赛关联题目")
    @PostMapping("/addCompetitionQuestion")
    public String addCompetitionQuestion(@RequestParam("token")String token, @RequestBody CompetitionQuestionBank competitionQuestionBank) {
        //身份验证过期 or 权限不足
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        ThreadLocalUtil.mapThreadLocal.remove();
        //报错排查
        if ( map.get("error") != null) {
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return competitionQuestionBankService.addCompetitionQuestion(competitionQuestionBank);
    }
    /**
     * 通过比赛Id获取比赛题目
     */
    @ApiOperation("通过比赛Id获取比赛题目")
    @PostMapping("/getQuestionListByContestId")
    public String getQuestionListByContestId(@RequestParam("token")String token,@RequestParam("contestId") Long contestId){
        //身份验证过期 or 权限不足
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        ThreadLocalUtil.mapThreadLocal.remove();
        //报错排查
        if ( map.get("error") != null) {
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return competitionQuestionBankService.getQuestionListByContestId(contestId);
    }

    /**
     * 获取比赛题目id
     * @param token
     * @param contestId
     * @return
     */
    @ApiOperation("获取比赛题目")
    @GetMapping("/getQuestionListInContest")
    public String getQuestionListInContest(@RequestParam("token")String token, @RequestParam("contestId")Long contestId){
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        ThreadLocalUtil.mapThreadLocal.remove();
        //报错排查
        if ( map.get("error") != null) {
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return competitionQuestionBankService.getQuestionListInContest(contestId);
    }

    /**
     * 管理员获取比赛题目id
     * @param token
     * @param contestId
     * @return
     */
    @ApiOperation("获取比赛题目")
    @GetMapping("/admin/getQuestionListInContest")
    public String adminGetQuestionListInContest(@RequestParam("token")String token, @RequestParam("contestId")Long contestId){
        //身份验证过期
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        ThreadLocalUtil.mapThreadLocal.remove();
        if (map.get("error") != null){
            System.out.println("权限不足"+map);
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return competitionQuestionBankService.getQuestionListInContest(contestId);
    }

    /**
     * 监考人员获取比赛题目id
     * @param token
     * @param contestId
     * @return
     */
    @ApiOperation("监考人员获取比赛题目")
    @GetMapping("/proctor/getQuestionListInContest")
    public String proctorGetQuestionListInContest(@RequestParam("token")String token, @RequestParam("contestId")Long contestId){
        //身份验证过期
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        ThreadLocalUtil.mapThreadLocal.remove();
        if (map.get("error") != null){
            System.out.println("权限不足"+map);
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return competitionQuestionBankService.getQuestionListInContest(contestId);
    }
}
