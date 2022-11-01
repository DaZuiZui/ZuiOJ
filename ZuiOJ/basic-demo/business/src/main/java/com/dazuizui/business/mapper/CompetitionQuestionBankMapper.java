package com.dazuizui.business.mapper;

import com.dazuizui.basicapi.entry.CompetitionQuestionBank;
import org.apache.ibatis.annotations.Mapper;

/**
 * 题目与比赛关联
 */
@Mapper
public interface CompetitionQuestionBankMapper {
    public Long addQuestionInContest(CompetitionQuestionBank competitionQuestionBank);
}
