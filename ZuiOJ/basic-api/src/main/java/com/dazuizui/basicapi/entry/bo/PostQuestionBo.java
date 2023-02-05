package com.dazuizui.basicapi.entry.bo;

import com.dazuizui.basicapi.entry.ProblemLimit;

import java.io.Serializable;

/**
 * 提交问题实体BO
 */
public class PostQuestionBo implements Serializable {
    //问题Bo
    private QuestionBankBo questionBankBo;
    //问题限制
    private ProblemLimit problemLimit;

    @Override
    public String toString() {
        return "PostQuestionBo{" +
                "questionBankBo=" + questionBankBo +
                ", problemLimit=" + problemLimit +
                '}';
    }

    public QuestionBankBo getQuestionBankBo() {
        return questionBankBo;
    }

    public void setQuestionBankBo(QuestionBankBo questionBankBo) {
        this.questionBankBo = questionBankBo;
    }

    public ProblemLimit getProblemLimit() {
        return problemLimit;
    }

    public void setProblemLimit(ProblemLimit problemLimit) {
        this.problemLimit = problemLimit;
    }

    public PostQuestionBo() {
    }

    public PostQuestionBo(QuestionBankBo questionBankBo, ProblemLimit problemLimit) {
        this.questionBankBo = questionBankBo;
        this.problemLimit = problemLimit;
    }
}
