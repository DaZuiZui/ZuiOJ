package com.dazuizui.business.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 题库MD text 关联
 */
@Mapper
public interface QuestionBankDetailedMapper {
    /**
     * 批量删除题目集合的md text信息
     * @param list
     * @return
     */
    public Long batchDeleteMdtextOfQuestions(@Param("list") List list);
}
