package com.dazuizui.business.domain.bo;

import java.io.Serializable;

/**
 * 通过QuestionId和ContestId查询提交数据Bo
 */
public class QueryCountByContestIdAndQuestionIdBo implements Serializable {
    private Long contestId ;
    private Long questionId;
    private String token;
    private Long start;
    private Long end;

    @Override
    public String toString() {
        return "QueryCountByContestIdAndQuestionIdBo{" +
                "contestId=" + contestId +
                ", questionId=" + questionId +
                ", token='" + token + '\'' +
                ", start=" + start +
                ", end=" + end +
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    public QueryCountByContestIdAndQuestionIdBo() {
    }

    public QueryCountByContestIdAndQuestionIdBo(Long contestId, Long questionId, String token, Long start, Long end) {
        this.contestId = contestId;
        this.questionId = questionId;
        this.token = token;
        this.start = start;
        this.end = end;
    }
}
