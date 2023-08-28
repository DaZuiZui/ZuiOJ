package com.dazuizui.business.domain;

import java.io.Serializable;

public class UserMaxCoverageRate implements Serializable {
    private Long questionId;
    private Double coverageRate;
    private String questionName;

    public UserMaxCoverageRate() {
    }

    @Override
    public String toString() {
        return "UserMaxCoverageRate{" +
                "questionId=" + questionId +
                ", coverageRate=" + coverageRate +
                ", questionName='" + questionName + '\'' +
                '}';
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Double getCoverageRate() {
        return coverageRate;
    }

    public void setCoverageRate(Double coverageRate) {
        this.coverageRate = coverageRate;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public UserMaxCoverageRate(Long questionId, Double coverageRate, String questionName) {
        this.questionId = questionId;
        this.coverageRate = coverageRate;
        this.questionName = questionName;
    }
}
