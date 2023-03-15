package com.dazuizui.business.domain.bo;

import java.io.Serializable;

/**
 * 管理员添加比赛选手数据
 */
public class AdminAddCompetitionInfoBo implements Serializable {
    private String username;    //用户名
    private Long contestId;     //比赛id
    private String token;       //token
    private String nonPowerToken; //米等性

    @Override
    public String toString() {
        return "AdminAddCompetitionInfoBo{" +
                "username='" + username + '\'' +
                ", contestId=" + contestId +
                ", token='" + token + '\'' +
                ", nonPowerToken='" + nonPowerToken + '\'' +
                '}';
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

    public AdminAddCompetitionInfoBo() {
    }

    public AdminAddCompetitionInfoBo(String username, Long contestId, String token, String nonPowerToken) {
        this.username = username;
        this.contestId = contestId;
        this.token = token;
        this.nonPowerToken = nonPowerToken;
    }
}
