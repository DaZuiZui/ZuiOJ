package com.dazuizui.business.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 问题案例属性
 */
@Mapper
public interface QuestionCaseAttributeMapper {
    /**
     * 添加题目个数属性
     * @return
     */
    public Long insertQuestionCaseAttribute(@Param("questionId")Long questionId);

    /**
     * 修改题目个数属性
     * @param questionId
     * @param num
     * @param type 个数属性
     * @return
     */
    public Long updateQuestionCaseAttribute(@Param("questionId")Long questionId,@Param("num")Long num,@Param("type")Integer type);
}
