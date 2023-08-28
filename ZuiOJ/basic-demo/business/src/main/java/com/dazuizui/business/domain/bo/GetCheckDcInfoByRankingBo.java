package com.dazuizui.business.domain.bo;

import java.io.Serializable;

/**
 * 该实体用来查重用户提交代码的重复率通过排名。
 */
public class GetCheckDcInfoByRankingBo implements Serializable {
    private String token;
    public Long contestId;
    private Long start;
    private Long size;

    @Override
    public String toString() {
        return "GetCheckDcInfoByRankingBo{" +
                "token='" + token + '\'' +
                ", contestId=" + contestId +
                ", start=" + start +
                ", size=" + size +
                '}';
    }

    public GetCheckDcInfoByRankingBo() {
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

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public GetCheckDcInfoByRankingBo(String token, Long contestId, Long start, Long size) {
        this.token = token;
        this.contestId = contestId;
        this.start = start;
        this.size = size;
    }
}
