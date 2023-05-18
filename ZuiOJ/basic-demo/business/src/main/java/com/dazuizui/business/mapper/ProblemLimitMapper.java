package com.dazuizui.business.mapper;

import com.dazuizui.basicapi.entry.ProblemLimit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 增加问题限制
     * @return
     */
    public Long addProblemLimit(ProblemLimit problemLimit);

    /**
     * 通过id修改题目
     * @param problemLimit
     * @return
     */
    public Long updateTheprolemLimitById(ProblemLimit problemLimit);

    /**
     * 删除题解通过question id list
     * @param list
     * @return
     */
    public Long deleteByQuestionIdList(@Param("list")List<Long> list);


}
