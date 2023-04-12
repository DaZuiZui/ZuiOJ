package com.dazuizui.business.domain.bo;

import java.io.Serializable;

/**
 * 添加监考人员Bo页面
 */
public class AddProctorBo implements Serializable {
    private String token;
    private String username;
    private Long contestId;

    @Override
    public String toString() {
        return "AddProctorBo{" +
                "token='" + token + '\'' +
                ", username='" + username + '\'' +
                ", contestId=" + contestId +
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

    public AddProctorBo() {
    }

    public AddProctorBo(String token, String username, Long contestId) {
        this.token = token;
        this.username = username;
        this.contestId = contestId;
    }
}
