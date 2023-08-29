package com.dazuizui.business.domain.bo;

import java.io.Serializable;

/**
 * 该实体用来查看用户详细的查重报告，=
 */
public class FindByContestIdAndQuestionIdAndMasterOdBo implements Serializable {
    private Long contestId;
    private Long questionId;
    private Long masterId;
    private Integer start;
    private Integer size;

    @Override
    public String toString() {
        return "FindByContestIdAndQuestionIdAndMasterOdBo{" +
                "contestId=" + contestId +
                ", questionId=" + questionId +
                ", masterId=" + masterId +
                ", start=" + start +
                ", size=" + size +
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

    public Long getMasterId() {
        return masterId;
    }

    public void setMasterId(Long masterId) {
        this.masterId = masterId;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public FindByContestIdAndQuestionIdAndMasterOdBo() {
    }

    public FindByContestIdAndQuestionIdAndMasterOdBo(Long contestId, Long questionId, Long masterId, Integer start, Integer size) {
        this.contestId = contestId;
        this.questionId = questionId;
        this.masterId = masterId;
        this.start = start;
        this.size = size;
    }
}
