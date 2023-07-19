package com.dazuizui.business.domain.bo;

import com.dazuizui.business.domain.User;

import java.io.Serializable;

/**
 * 通过Id修改用户BO
 */
public class UpdateUserInfoByIdBo implements Serializable {
    private User user;
    private String token;

    @Override
    public String toString() {
        return "UpdateUserInfoByIdBo{" +
                "user=" + user +
                ", token='" + token + '\'' +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UpdateUserInfoByIdBo() {
    }

    public UpdateUserInfoByIdBo(User user, String token) {
        this.user = user;
        this.token = token;
    }
}
