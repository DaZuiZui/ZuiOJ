package com.dazuizui.business.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
     * 添加题解
     * @param questionId 问题id
     * @param type       类型
     * @param num        数值
     * @return
     */
    public Long increaseTheNumberOfSolutions(@Param("questionId")Long questionId,@Param("privacy")Integer privacy,@Param("type")Integer type,@Param("num")Long num);

    /**
     * 查看题解数量
     */
    public Long queryNumberOfQuestionByStatus(@Param("questionId")Long questionId,@Param("status")Integer status);

    /**
     * 批量删除题解属性通过question id list
     * @param list
     * @return
     */
    public Long deleteQuestionAnswerAttributeByQuestionIdList(@Param("list") List list);
}
