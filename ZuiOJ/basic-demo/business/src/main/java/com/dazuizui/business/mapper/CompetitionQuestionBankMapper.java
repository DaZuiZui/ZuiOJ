package com.dazuizui.business.mapper;

import com.dazuizui.basicapi.entry.CompetitionQuestionBank;
import com.dazuizui.basicapi.entry.vo.ContestQuestionVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 题目与比赛关联
 */
@Mapper
public interface CompetitionQuestionBankMapper {
    /**
     * 添加题目与比赛关联
     * @param competitionQuestionBank
     * @return
     */
    public Long addQuestionInContest(CompetitionQuestionBank competitionQuestionBank);

    /**
     * 通过比赛Id获取比赛题目
     */
    public List<ContestQuestionVo> getQuestionListByContestId(@Param("contestId")Long contestId);
    /**
     * 获取比赛题目还有用户AC次数
     * @param contestId
     * @return
     */
    public List<ContestQuestionVo> getQuestionListInContest(@Param("contestId") Long contestId,@Param("userId")Long userId);
}
