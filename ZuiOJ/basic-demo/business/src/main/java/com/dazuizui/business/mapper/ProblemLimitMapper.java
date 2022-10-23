package com.dazuizui.business.mapper;

import com.dazuizui.basicapi.entry.ProblemLimit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *
 */
@Mapper
public interface ProblemLimitMapper {
    /**
     * 通过题库主键去查询此题的限制
     * @param questionId
     * @return
     */
    public ProblemLimit queryTheProblemLimitByQuestionId(@Param("questionId")long questionId);
}
