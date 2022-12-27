package com.dazuizui.basicapi.entry.bo;

import java.io.Serializable;

/**
 * 删除问题BO
 */
public class DeleteQuestion implements Serializable {
    private Long id;
    private Long questionType;
    private String token;

    @Override
    public String toString() {
        return "DeleteQuestion{" +
                "id=" + id +
                ", questionType=" + questionType +
                ", token='" + token + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Long questionType) {
        this.questionType = questionType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public DeleteQuestion() {
    }

    public DeleteQuestion(Long id, Long questionType, String token) {
        this.id = id;
        this.questionType = questionType;
        this.token = token;
    }
}
