package com.dazuizui.business.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 监考
 */
public class Proctor implements Serializable {
    private Long id;
    private Long userId;
    private Long contestId;
    private Long createBy;
    private Long updateBy;
    private Date updateTime;
    private Date createTime;
    private Integer status;
    private Integer delFLag;

    @Override
    public String toString() {
        return "Proctor{" +
                "id=" + id +
                ", userId=" + userId +
                ", contestId=" + contestId +
                ", createBy=" + createBy +
                ", updateBy=" + updateBy +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                ", status=" + status +
                ", delFLag=" + delFLag +
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

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    public Integer getDelFLag() {
        return delFLag;
    }

    public void setDelFLag(Integer delFLag) {
        this.delFLag = delFLag;
    }

    public Proctor() {

    }

    public Proctor(Long id, Long userId, Long contestId, Long createBy, Long updateBy, Date updateTime, Date createTime, Integer status, Integer delFLag) {
        this.id = id;
        this.userId = userId;
        this.contestId = contestId;
        this.createBy = createBy;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
        this.createTime = createTime;
        this.status = status;
        this.delFLag = delFLag;
    }
}
