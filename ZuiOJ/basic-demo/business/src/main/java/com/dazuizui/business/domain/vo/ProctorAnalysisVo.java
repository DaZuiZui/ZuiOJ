package com.dazuizui.business.domain.vo;

import com.dazuizui.basicapi.entry.User;

import java.io.Serializable;

public class ProctorAnalysisVo implements Serializable {
    private User user;
    private boolean isProctor;

    @Override
    public String toString() {
        return "ProctorAnalysisVo{" +
                "user=" + user +
                ", isProctor=" + isProctor +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isProctor() {
        return isProctor;
    }

    public void setProctor(boolean proctor) {
        isProctor = proctor;
    }

    public ProctorAnalysisVo() {
    }

    public ProctorAnalysisVo(User user, boolean isProctor) {
        this.user = user;
        this.isProctor = isProctor;
    }
}
