package com.dazuizui.business.domain.bo;

import java.io.Serializable;

/**
 * 筛选获取比赛时提交代码信息
 */
public class FilterQueryMatchSaveCodeBo implements Serializable {
    private Long contestId;
    private Long questionId;
    private Long userId;
    private String token;
    private Integer page;
    private Integer size;
    private Integer status;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FilterQueryMatchSaveCodeBo{" +
                "contestId=" + contestId +
                ", questionId=" + questionId +
                ", userId=" + userId +
                ", token='" + token + '\'' +
                ", page=" + page +
                ", size=" + size +
                ", status=" + status +
                '}';
    }

    public FilterQueryMatchSaveCodeBo() {
    }

    public FilterQueryMatchSaveCodeBo(Long contestId, Long questionId, Long userId, String token, Integer page, Integer size, Integer status) {
        this.contestId = contestId;
        this.questionId = questionId;
        this.userId = userId;
        this.token = token;
        this.page = page;
        this.size = size;
        this.status = status;
    }
}
