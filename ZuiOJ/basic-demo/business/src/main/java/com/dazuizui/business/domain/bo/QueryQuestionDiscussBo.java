package com.dazuizui.business.domain.bo;

import java.io.Serializable;

/**
 * 查询问题讨论Bo
 */
public class QueryQuestionDiscussBo implements Serializable {
    private Long questionId;
    private Integer page;
    private Integer size;


    @Override
    public String toString() {
        return "QueryQuestionDiscussBo{" +
                "questionId=" + questionId +
                ", page=" + page +
                ", size=" + size +
                '}';
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public QueryQuestionDiscussBo() {
    }

    public QueryQuestionDiscussBo(Long questionId, Integer page, Integer size) {
        this.questionId = questionId;
        this.page = page;
        this.size = size;
    }
}
