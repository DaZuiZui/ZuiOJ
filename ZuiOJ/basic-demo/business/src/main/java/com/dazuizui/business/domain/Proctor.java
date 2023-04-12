package com.dazuizui.business.domain;

import java.io.Serializable;

/**
 * 监考
 */
public class Proctor implements Serializable {
    public Long id;
    public Long userId;
    public Long contestId;

    @Override
    public String toString() {
        return "Proctor{" +
                "id=" + id +
                ", userId=" + userId +
                ", contestId=" + contestId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    public Proctor() {
    }

    public Proctor(Long id, Long userId, Long contestId) {
        this.id = id;
        this.userId = userId;
        this.contestId = contestId;
    }
}
