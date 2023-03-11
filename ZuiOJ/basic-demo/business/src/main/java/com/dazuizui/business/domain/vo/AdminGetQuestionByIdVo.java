package com.dazuizui.business.domain.vo;

import com.dazuizui.basicapi.entry.ProblemLimit;
import com.dazuizui.basicapi.entry.vo.QuestionBankVo;

import java.io.Serializable;

/**
 * 管理员获取问题通过问题id
 */
public class AdminGetQuestionByIdVo implements Serializable {
    private QuestionBankVo questionBankVo;
    private ProblemLimit problemLimitById;

    @Override
    public String toString() {
        return "AdminGetQuestionByIdVo{" +
                "questionBankVo=" + questionBankVo +
                ", problemLimitById=" + problemLimitById +
                '}';
    }

    public QuestionBankVo getQuestionBankVo() {
        return questionBankVo;
    }

    public void setQuestionBankVo(QuestionBankVo questionBankVo) {
        this.questionBankVo = questionBankVo;
    }

    public ProblemLimit getProblemLimitById() {
        return problemLimitById;
    }

    public void setProblemLimitById(ProblemLimit problemLimitById) {
        this.problemLimitById = problemLimitById;
    }

    public AdminGetQuestionByIdVo() {
    }

    public AdminGetQuestionByIdVo(QuestionBankVo questionBankVo, ProblemLimit problemLimitById) {
        this.questionBankVo = questionBankVo;
        this.problemLimitById = problemLimitById;
    }
}
