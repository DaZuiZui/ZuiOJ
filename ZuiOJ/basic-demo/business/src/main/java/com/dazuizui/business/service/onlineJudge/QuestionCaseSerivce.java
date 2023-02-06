package com.dazuizui.business.service.onlineJudge;


import com.dazuizui.basicapi.entry.bo.AddQuestionCaseBo;
import org.springframework.stereotype.Service;
import com.dazuizui.basicapi.entry.bo.AdminQueryQuestionCaseBo;

/**
 * 题库案例
 */
@Service
public interface QuestionCaseSerivce {
    /**
     * 添加问题案例
     * @param addQuestionCaseBo
     * @return
     */
    public String addQuestionCase(AddQuestionCaseBo addQuestionCaseBo);
    /**
     * 通过问题id查询题目案例
     * @param adminQueryQuestionCaseBo
     * @return
     */
    public String queryTheQuestionCasesByQuestionId(AdminQueryQuestionCaseBo adminQueryQuestionCaseBo);
}
