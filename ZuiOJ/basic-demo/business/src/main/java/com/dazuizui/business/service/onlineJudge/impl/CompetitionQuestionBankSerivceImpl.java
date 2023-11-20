package com.dazuizui.business.service.onlineJudge.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.CompetitionQuestionBank;
import com.dazuizui.basicapi.entry.Contest;
import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.StatusCodeMessage;
import com.dazuizui.basicapi.entry.vo.ContestQuestionVo;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.mapper.CompetitionQuestionBankMapper;
import com.dazuizui.business.mapper.ContestMapper;
import com.dazuizui.business.service.onlineJudge.CompetitionQuestionBankService;
import com.dazuizui.business.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 比赛与题关联实现接口
 */
@Service
public class CompetitionQuestionBankSerivceImpl implements CompetitionQuestionBankService {
    @Autowired
    private CompetitionQuestionBankMapper competitionQuestionBankMapper;
    @Autowired
    private ContestMapper contestMapper;

    /**
     * 删除比赛关联题目
     * @param questionId
     * @param contestId
     * @return
     */
    @Override
    public String removeQuestion(Long questionId, Long contestId) {
        Long aLong = competitionQuestionBankMapper.removeQuestion(questionId, contestId);
        if (aLong == 0){
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
        }
        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,null, StatusCode.OK));
    }

    /**
     * 添加比赛关联题目
     * @return
     */
    @Override
    public String addCompetitionQuestion(CompetitionQuestionBank competitionQuestionBank){
        //校验题目是否存在
        Long countByContestIdAndQeustionId = competitionQuestionBankMapper.findCountByContestIdAndQeustionId(competitionQuestionBank.getContestId(), competitionQuestionBank.getQuestionId());
        if (countByContestIdAndQeustionId.longValue() != 0){
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.QuestionExists,null, StatusCode.QuestionExists));
        }

        Long aLong = competitionQuestionBankMapper.addQuestionInContest(competitionQuestionBank);
        if (aLong == 0){
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
        }
        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,null, StatusCode.OK));
    }

    /**
     * 通过比赛Id获取比赛题目
     */
    @Override
    public String getQuestionListByContestId(Long contestId) {
        List<ContestQuestionVo> questionListByContestId = competitionQuestionBankMapper.getQuestionListByContestId(contestId);
        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,questionListByContestId, StatusCode.OK));
    }

    /**
     * 添加题在比赛题库中
     * out 淘汰计划
     * @return
     */
    @Override
    public String addQuestionInContest(CompetitionQuestionBank competitionQuestionBank) {

        return null;
    }

    /**
     * 获取比赛题目
     * @param contestId
     * @return
     */
    @Override
    public String getQuestionListInContest(Long contestId) {
        //获取比赛人ID
        String idstring = (String) ThreadLocalUtil.mapThreadLocalOfJWT.get().get("userinfo").get("id");

        //查看当前时间是否可以获取比赛
        Contest eventById = contestMapper.getEventById(contestId);
        Date date = new Date();
        if (date.compareTo(eventById.getStartTime()) < 0){
            return JSONArray.toJSONString(new ResponseVo<>("未到达获取题目时间",null,"0x945"));
        }

        //获取题库
        List<ContestQuestionVo> questionListInContest = competitionQuestionBankMapper.getQuestionListInContest(contestId,Long.valueOf(idstring));

        return JSONArray.toJSONString(new ResponseVo<>("获取比赛中的题库",questionListInContest,"200"));
    }
}
