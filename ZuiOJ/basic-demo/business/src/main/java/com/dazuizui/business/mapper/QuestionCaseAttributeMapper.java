package com.dazuizui.business.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 问题案例属性
 */
@Mapper
public interface QuestionCaseAttributeMapper {
    /**
     * 查询指定状态的数据
     * @param status
     * @return
     */
    public Long queryCountOfAnyStatus(@Param("status")Integer status,@Param("questionId")Long questionId);

    /**
     * 添加题目个数属性
     * @return
     */
    public Long insertQuestionCaseAttribute(@Param("questionId")Long questionId);

    /**
     * 修改题目个数属性
     * @param questionId
     * @param num
     * @param type 个数属性类型 1为添加
     * @return
     */
    public Long updateQuestionCaseAttribute(@Param("questionId")Long questionId,@Param("num")Integer num,@Param("type")Integer type);

    /**
     *  delte question case attribute by question id list
     * @param list
     * @return
     */
    public Long deleteByQuestionidList(@Param("list") List list);
}
