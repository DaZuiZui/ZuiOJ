package com.dazuizui.business.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 参赛选手信息
 */
public class CompetitionInfoInContest implements Serializable {
    private Long id;
    private Long contestId;
    private String createByName;
    private String competitionName;
    private Date createTime;
    private Integer status;

    @Override
    public String toString() {
        return "CompetitionInfoInContest{" +
                "id=" + id +
                ", contestId=" + contestId +
                ", createByName='" + createByName + '\'' +
                ", competitionName='" + competitionName + '\'' +
                ", createTime=" + createTime +
                ", status=" + status +
                '}';
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

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public CompetitionInfoInContest() {
    }

    public CompetitionInfoInContest(Long id, Long contestId, String createByName, String competitionName, Date createTime, Integer status) {
        this.id = id;
        this.contestId = contestId;
        this.createByName = createByName;
        this.competitionName = competitionName;
        this.createTime = createTime;
        this.status = status;
    }
}
