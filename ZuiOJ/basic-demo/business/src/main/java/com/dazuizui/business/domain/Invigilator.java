package com.dazuizui.business.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 监考实体
 */
public class Invigilator implements Serializable {
    private Long id;
    private Long contestId;
    private Long userId;
    private Integer role;
    private Long createBy;
    private Date date;
    private Long updateBy;
    private Long updateTime;
    private Integer status;
    private Integer delFlag;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return "Invigilator{" +
                "id=" + id +
                ", contestId=" + contestId +
                ", userId=" + userId +
                ", role=" + role +
                ", createBy=" + createBy +
                ", date=" + date +
                ", updateBy=" + updateBy +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", delFlag=" + delFlag +
                '}';
    }

    public Invigilator() {
    }

    public Invigilator(Long id, Long contestId, Long userId, Integer role, Long createBy, Date date, Long updateBy, Long updateTime, Integer status, Integer delFlag) {
        this.id = id;
        this.contestId = contestId;
        this.userId = userId;
        this.role = role;
        this.createBy = createBy;
        this.date = date;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
        this.status = status;
        this.delFlag = delFlag;
    }
}
