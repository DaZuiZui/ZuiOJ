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
    /**
     * 通过问题id查询题目案例
     * @param questionId
     * @return
     */
    public List<QuestionCase> queryTheQuestionCasesByQuestionId(@Param("questionId")long questionId);
}
