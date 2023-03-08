package com.dazuizui.business.domain.bo;

import java.io.Serializable;

/**
 * 查询竞赛日志Bo
 */
public class QueryContestSubmissionLogBo implements Serializable {
    private String token;
    private Long page;
    private Long size;
    private Long contestId;

    @Override
    public String toString() {
        return "QueryContestSubmissionLogBo{" +
                "token='" + token + '\'' +
                ", page=" + page +
                ", size=" + size +
                ", contestId=" + contestId +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    public QueryContestSubmissionLogBo() {
    }

    public QueryContestSubmissionLogBo(String token, Long page, Long size, Long contestId) {
        this.token = token;
        this.page = page;
        this.size = size;
        this.contestId = contestId;
    }
}
