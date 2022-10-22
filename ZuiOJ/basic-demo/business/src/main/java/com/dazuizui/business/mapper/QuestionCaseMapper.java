package com.dazuizui.business.mapper;

import com.dazuizui.basicapi.entry.QuestionCase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 问题案例Mapper
 */
@Mapper
public interface QuestionCaseMapper {
    public List<QuestionCase> queryTheQuestionCasesByQuestionId(@Param("questionId")long questionId);
}
