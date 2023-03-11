package com.dazuizui.business.service.onlineJudge;


import com.dazuizui.basicapi.entry.bo.AddQuestionCaseBo;
import com.dazuizui.business.domain.bo.UpdateQuestionCaseBo;
import org.springframework.stereotype.Service;
import com.dazuizui.basicapi.entry.bo.AdminQueryQuestionCaseBo;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 题库案例
 */
@Service
public interface QuestionCaseSerivce {
    /**
     * 通过案例id删除案例
     * @param id            案例id
     * @param questionId    问题id
     * @return
     */
    public String deleteCaseByCaseId(Long id,Long questionId);
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

    /**
     * 修改案例
     * @param updateQuestionCaseBo
     * @return
     */
    public String updateQuestionCase(@RequestBody UpdateQuestionCaseBo updateQuestionCaseBo);
}
