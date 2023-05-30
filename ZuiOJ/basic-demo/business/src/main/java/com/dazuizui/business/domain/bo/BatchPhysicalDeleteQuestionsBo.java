package com.dazuizui.business.domain.bo;

import java.io.Serializable;
import java.util.List;

/**
 * 物理批量删除Vo
 */
public class BatchPhysicalDeleteQuestionsBo implements Serializable {
    private String token;
    private List<Long> questionList;

    @Override
    public String toString() {
        return "BatchPhysicalDeleteQuestionsBo{" +
                "token='" + token + '\'' +
                ", questionList=" + questionList +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Long> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Long> questionList) {
        this.questionList = questionList;
    }

    public BatchPhysicalDeleteQuestionsBo() {
    }

    public BatchPhysicalDeleteQuestionsBo(String token, List<Long> questionList) {
        this.token = token;
        this.questionList = questionList;
    }
}
