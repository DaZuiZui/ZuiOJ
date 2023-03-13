package com.dazuizui.business.domain.bo;

import com.dazuizui.basicapi.entry.ProblemLimit;
import com.dazuizui.basicapi.entry.vo.QuestionBankVo;

import java.io.Serializable;

/**
 * 修改题目和限制
 */
public class UpdateQuestionAndLimitByQuestionIdBo implements Serializable {
    private ProblemLimit problemLimit;
    private QuestionBankVo questionBankVo;
    private String token;
    private Integer oldstatus;  //旧的选择

    @Override
    public String toString() {
        return "UpdateQuestionAndLimitByQuestionIdBo{" +
                "problemLimit=" + problemLimit +
                ", questionBankVo=" + questionBankVo +
                ", token='" + token + '\'' +
                ", oldstatus=" + oldstatus +
                '}';
    }

    public ProblemLimit getProblemLimit() {
        return problemLimit;
    }

    public void setProblemLimit(ProblemLimit problemLimit) {
        this.problemLimit = problemLimit;
    }

    public QuestionBankVo getQuestionBankVo() {
        return questionBankVo;
    }

    public void setQuestionBankVo(QuestionBankVo questionBankVo) {
        this.questionBankVo = questionBankVo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getOldstatus() {
        return oldstatus;
    }

    public void setOldstatus(Integer oldstatus) {
        this.oldstatus = oldstatus;
    }

    public UpdateQuestionAndLimitByQuestionIdBo() {
    }

    public UpdateQuestionAndLimitByQuestionIdBo(ProblemLimit problemLimit, QuestionBankVo questionBankVo, String token, Integer oldstatus) {
        this.problemLimit = problemLimit;
        this.questionBankVo = questionBankVo;
        this.token = token;
        this.oldstatus = oldstatus;
    }
}
