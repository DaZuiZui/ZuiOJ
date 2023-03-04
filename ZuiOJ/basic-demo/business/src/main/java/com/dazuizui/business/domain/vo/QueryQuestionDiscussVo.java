package com.dazuizui.business.domain.vo;

import com.dazuizui.business.domain.QuestionDiscuss;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询问题讨论VO
 */
public class QueryQuestionDiscussVo implements Serializable {
    private Long totalElements;
    private List<QuestionDiscuss> questionDiscusses;

    @Override
    public String toString() {
        return "QueryQuestionDiscussVo{" +
                "totalElements=" + totalElements +
                ", questionDiscusses=" + questionDiscusses +
                '}';
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public List<QuestionDiscuss> getQuestionDiscusses() {
        return questionDiscusses;
    }

    public void setQuestionDiscusses(List<QuestionDiscuss> questionDiscusses) {
        this.questionDiscusses = questionDiscusses;
    }

    public QueryQuestionDiscussVo() {
    }

    public QueryQuestionDiscussVo(Long totalElements, List<QuestionDiscuss> questionDiscusses) {
        this.totalElements = totalElements;
        this.questionDiscusses = questionDiscusses;
    }
}
