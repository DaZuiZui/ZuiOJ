package com.dazuizui.business.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 题解数量Mapper
 */
@Mapper
public interface QuestionAnswerAttributeMapper {
    /**
     * 创建题解数量
     * @param questionId
     * @return
     */
    public Long addQuestionAnswerAttributeMapper(@Param("questionId")Long questionId);

    /**
     *  增加题解数量
     */
    public Long increaseTheNumberOfSolutions(@Param("questionId")Long questionId);
}
