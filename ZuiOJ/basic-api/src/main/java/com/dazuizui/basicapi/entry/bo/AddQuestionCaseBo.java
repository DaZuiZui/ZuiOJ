package com.dazuizui.basicapi.entry.bo;

import com.dazuizui.basicapi.entry.QuestionCase;

import java.io.Serializable;
import java.util.List;

/**
 * 添加案例BO
 */
public class AddQuestionCaseBo implements Serializable {
    //用户token
    private String token;
    //问题案例集合
    private List<QuestionCase> questionCases;
    //幂等性
    private String nonPowerToken;
    //问题id
    private Long questionId;

    @Override
    public String toString() {
        return "AddQuestionCaseBo{" +
                "token='" + token + '\'' +
                ", questionCases=" + questionCases +
                ", nonPowerToken='" + nonPowerToken + '\'' +
                ", questionId=" + questionId +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<QuestionCase> getQuestionCases() {
        return questionCases;
    }

    public void setQuestionCases(List<QuestionCase> questionCases) {
        this.questionCases = questionCases;
    }

    public String getNonPowerToken() {
        return nonPowerToken;
    }

    public void setNonPowerToken(String nonPowerToken) {
        this.nonPowerToken = nonPowerToken;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public AddQuestionCaseBo() {
    }

    public AddQuestionCaseBo(String token, List<QuestionCase> questionCases, String nonPowerToken, Long questionId) {
        this.token = token;
        this.questionCases = questionCases;
        this.nonPowerToken = nonPowerToken;
        this.questionId = questionId;
    }
}
