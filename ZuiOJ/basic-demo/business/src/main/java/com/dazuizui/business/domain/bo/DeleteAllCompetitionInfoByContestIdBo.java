package com.dazuizui.business.domain.bo;

import java.io.Serializable;

/**
 * 删除比赛选手通过比赛ID BO
 */
public class DeleteAllCompetitionInfoByContestIdBo implements Serializable {
    private String token;
    private Long contestId;

    @Override
    public String toString() {
        return "DeleteAllCompetitionInfoByContestIdBo{" +
                "token='" + token + '\'' +
                ", contestId=" + contestId +
                '}';
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

    public DeleteAllCompetitionInfoByContestIdBo() {
    }

    public DeleteAllCompetitionInfoByContestIdBo(String token, Long contestId) {
        this.token = token;
        this.contestId = contestId;
    }
}
