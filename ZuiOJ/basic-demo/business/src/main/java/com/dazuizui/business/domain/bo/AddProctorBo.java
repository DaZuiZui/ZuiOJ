package com.dazuizui.business.domain.bo;

import java.io.Serializable;

/**
 * 添加监考人员Bo页面
 */
public class AddProctorBo implements Serializable {
    private String token;
    private String username;
    private Long contestId;
    private String nonPowerToken;

    @Override
    public String toString() {
        return "AddProctorBo{" +
                "token='" + token + '\'' +
                ", username='" + username + '\'' +
                ", contestId=" + contestId +
                ", nonPowerToken='" + nonPowerToken + '\'' +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    public String getNonPowerToken() {
        return nonPowerToken;
    }

    public void setNonPowerToken(String nonPowerToken) {
        this.nonPowerToken = nonPowerToken;
    }

    public AddProctorBo() {
    }

    public AddProctorBo(String token, String username, Long contestId, String nonPowerToken) {
        this.token = token;
        this.username = username;
        this.contestId = contestId;
        this.nonPowerToken = nonPowerToken;
    }
}
