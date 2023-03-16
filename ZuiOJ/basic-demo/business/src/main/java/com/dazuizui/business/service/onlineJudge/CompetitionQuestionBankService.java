package com.dazuizui.business.service.onlineJudge;

import com.dazuizui.basicapi.entry.CompetitionQuestionBank;
import com.dazuizui.basicapi.entry.vo.ContestQuestionVo;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 比赛题目关联
 */
@Service
public interface CompetitionQuestionBankService {
    /**
     * 通过比赛Id获取比赛题目
     */
    public String getQuestionListByContestId(@Param("contestId")Long contestId);
    /**
     * 添加题在比赛题库中
     * @return
     */
    public String addQuestionInContest(CompetitionQuestionBank competitionQuestionBank);

    /**
     * 获取比赛题目id
     * @param contestId
     * @return
     */
    public String getQuestionListInContest( @RequestParam("contestId")Long contestId);
}
