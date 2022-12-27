package com.dazuizui.business.mapper;

import com.dazuizui.basicapi.entry.QuestionBank;
import com.dazuizui.basicapi.entry.QuestionCase;
import com.dazuizui.basicapi.entry.bo.AdminQueryQuestionCaseBo;
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

    /**
     * 删除案例
     * @param id
     */
    public Long deleteCaseById(@Param("id")Long id);

    /**
     * 管理员分页查找案例
     * @param adminQueryQuestionCaseBo
     * @return
     */
    public List<QuestionCase> pagingToGetQuestionCase(AdminQueryQuestionCaseBo adminQueryQuestionCaseBo);

    /**
     * 通过id查询指定数量
     */
    public Long queryCountOfCase(@Param("questionId") Long questionId);
}
