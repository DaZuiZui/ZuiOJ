package com.dazuizui.business.domain.bo;

import java.io.Serializable;

public class DeleteTheCompetitionByIdBo implements Serializable {
    private String token;
    private Long id;
    private Long contestId;

    @Override
    public String toString() {
        return "DeleteTheCompetitionByIdBo{" +
                "token='" + token + '\'' +
                ", id=" + id +
                ", contestId=" + contestId +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    public DeleteTheCompetitionByIdBo() {
    }

    public DeleteTheCompetitionByIdBo(String token, Long id, Long contestId) {
        this.token = token;
        this.id = id;
        this.contestId = contestId;
    }
}
