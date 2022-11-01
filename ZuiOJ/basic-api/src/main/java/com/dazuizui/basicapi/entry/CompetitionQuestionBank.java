package com.dazuizui.basicapi.entry;

import java.io.Serializable;

/**
 * 比赛与题的关联
 */
public class CompetitionQuestionBank implements Serializable {
    private Long id;
    private Long questionId;
    private Long contestId;

    @Override
    public String toString() {
        return "CompetitionQuestionBank{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", contestId=" + contestId +
                '}';
    }

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

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    public CompetitionQuestionBank() {
    }

    public CompetitionQuestionBank(Long id, Long questionId, Long contestId) {
        this.id = id;
        this.questionId = questionId;
        this.contestId = contestId;
    }
}
