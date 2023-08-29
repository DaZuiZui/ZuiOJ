package com.dazuizui.business.domain;

import java.io.Serializable;

public class UserMaxCoverageRate implements Serializable {
    private Long questionId;
    private Double coverageRate;
    private String questionName;
    private String name;
    private Long masterId;

    @Override
    public String toString() {
        return "UserMaxCoverageRate{" +
                "questionId=" + questionId +
                ", coverageRate=" + coverageRate +
                ", questionName='" + questionName + '\'' +
                ", name='" + name + '\'' +
                ", masterId=" + masterId +
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMasterId() {
        return masterId;
    }

    public void setMasterId(Long masterId) {
        this.masterId = masterId;
    }

    public UserMaxCoverageRate() {
    }

    public UserMaxCoverageRate(Long questionId, Double coverageRate, String questionName, String name, Long masterId) {
        this.questionId = questionId;
        this.coverageRate = coverageRate;
        this.questionName = questionName;
        this.name = name;
        this.masterId = masterId;
    }
}
