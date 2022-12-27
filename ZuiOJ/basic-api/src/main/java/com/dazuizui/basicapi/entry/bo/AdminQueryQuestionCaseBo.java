package com.dazuizui.basicapi.entry.bo;

import java.io.Serializable;

/**
 * 管理员查询案例实体BO
 */
public class AdminQueryQuestionCaseBo implements Serializable {
    //题目id
    private Long questionId;
    //用户token
    private String token;
    //起始页面
    private Long pages;
    //一夜查询多少个
    private Long numbers;

    @Override
    public String toString() {
        return "AdminQueryQuestionCaseBo{" +
                "questionId=" + questionId +
                ", token='" + token + '\'' +
                ", pages=" + pages +
                ", numbers=" + numbers +
                '}';
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getPages() {
        return pages;
    }

    public void setPages(Long pages) {
        this.pages = pages;
    }

    public Long getNumbers() {
        return numbers;
    }

    public void setNumbers(Long numbers) {
        this.numbers = numbers;
    }

    public AdminQueryQuestionCaseBo() {
    }

    public AdminQueryQuestionCaseBo(Long questionId, String token, Long pages, Long numbers) {
        this.questionId = questionId;
        this.token = token;
        this.pages = pages;
        this.numbers = numbers;
    }
}
