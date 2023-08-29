package com.dazuizui.business.domain.bo;

import java.io.Serializable;

public class StartCheckingBo implements Serializable {
    private Long contestId;
    private String token;
    private String nonPowerToken;

    @Override
    public String toString() {
        return "StartCheckingBo{" +
                "contestId=" + contestId +
                ", token='" + token + '\'' +
                ", nonPowerToken='" + nonPowerToken + '\'' +
                '}';
    }

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNonPowerToken() {
        return nonPowerToken;
    }

    public void setNonPowerToken(String nonPowerToken) {
        this.nonPowerToken = nonPowerToken;
    }

    public StartCheckingBo() {
    }

    public StartCheckingBo(Long contestId, String token, String nonPowerToken) {
        this.contestId = contestId;
        this.token = token;
        this.nonPowerToken = nonPowerToken;
    }
}
