package com.dazuizui.business.domain;

import javax.naming.directory.SearchResult;
import java.io.Serializable;

/**
 * 查询题库数量
 */
public class QuestionBankAttribute implements Serializable {
    private Long id;
    private Long publicQuestion;
    private Long privateQuestion;
    private Long examQuestion;
    private Long delQuestion;
    private Long number;

    @Override
    public String toString() {
        return "QuestionBankAttributeMapper{" +
                "id=" + id +
                ", publicQuestion=" + publicQuestion +
                ", privateQuestion=" + privateQuestion +
                ", examQuestion=" + examQuestion +
                ", delQuestion=" + delQuestion +
                ", number=" + number +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPublicQuestion() {
        return publicQuestion;
    }

    public void setPublicQuestion(Long publicQuestion) {
        this.publicQuestion = publicQuestion;
    }

    public Long getPrivateQuestion() {
        return privateQuestion;
    }

    public void setPrivateQuestion(Long privateQuestion) {
        this.privateQuestion = privateQuestion;
    }

    public Long getExamQuestion() {
        return examQuestion;
    }

    public void setExamQuestion(Long examQuestion) {
        this.examQuestion = examQuestion;
    }

    public Long getDelQuestion() {
        return delQuestion;
    }

    public void setDelQuestion(Long delQuestion) {
        this.delQuestion = delQuestion;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public QuestionBankAttribute() {
    }

    public QuestionBankAttribute(Long id, Long publicQuestion, Long privateQuestion, Long examQuestion, Long delQuestion, Long number) {
        this.id = id;
        this.publicQuestion = publicQuestion;
        this.privateQuestion = privateQuestion;
        this.examQuestion = examQuestion;
        this.delQuestion = delQuestion;
        this.number = number;
    }
}
