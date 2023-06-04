package com.dazuizui.business.domain.bo;

import java.io.Serializable;

/**
 * 管理员获取用户信息
 */
public class AdminGetUserinfo implements Serializable {
    private String token;
    private Long  userId;

    @Override
    public String toString() {
        return "AdminGetUserinfo{" +
                "token='" + token + '\'' +
                ", userId=" + userId +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public AdminGetUserinfo() {
    }

    public AdminGetUserinfo(String token, Long userId) {
        this.token = token;
        this.userId = userId;
    }
}
