package com.dazuizui.business.domain.bo;

import java.io.Serializable;

/**
 * 该实体用来查重用户提交代码的重复率通过排名。
 */
public class GetCheckDcInfoByRankingBo implements Serializable {
    private String token;
    public Long contestId;
    private Integer start;
    private Integer size;

    @Override
    public String toString() {
        return "GetCheckDcInfoByRankingBo{" +
                "token='" + token + '\'' +
                ", contestId=" + contestId +
                ", start=" + start +
                ", size=" + size +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
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

    public GetCheckDcInfoByRankingBo() {
    }

    public GetCheckDcInfoByRankingBo(String token, Long contestId, Integer start, Integer size) {
        this.token = token;
        this.contestId = contestId;
        this.start = start;
        this.size = size;
    }
}
