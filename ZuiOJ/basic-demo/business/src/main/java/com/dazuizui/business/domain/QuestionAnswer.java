package com.dazuizui.business.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 题解关联实体
 */
public class QuestionAnswer implements Serializable {
    private Long id;
    private Long questionId;
    private Long articleId;
    private Long createBy;
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "QuestionAnswer{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", articleId=" + articleId +
                ", createBy=" + createBy +
                ", createTime=" + createTime +
                '}';
    }

    public QuestionAnswer() {
    }

    public QuestionAnswer(Long id, Long questionId, Long articleId, Long createBy, Date createTime) {
        this.id = id;
        this.questionId = questionId;
        this.articleId = articleId;
        this.createBy = createBy;
        this.createTime = createTime;
    }
}
