package com.dazuizui.business.domain.bo;

public class DcInfoBo {
    private Long contestId;
    private Long questionId;

    @Override
    public String toString() {
        return "DcInfoBo{" +
                "contestId=" + contestId +
                ", questionId=" + questionId +
                '}';
    }

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public DcInfoBo() {
    }

    public DcInfoBo(Long contestId, Long questionId) {
        this.contestId = contestId;
        this.questionId = questionId;
    }
}
