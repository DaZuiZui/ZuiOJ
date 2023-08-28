package com.dazuizui.business.domain;

import java.io.Serializable;
import java.util.Date;

public class Coverage implements Serializable {
    private Long id;
    private Long contestId;
    private Long questionId;
    private Long masterId;
    private Long masterCodeId;
    private Long guestId;
    private Long guestCodeId;
    private Double coverageRate;
    private Long createBy;
    private Date createTime;
    private Long updateBy;
    private Date updateTime;
    private Integer status;
    private Integer delFlag;

    @Override
    public String toString() {
        return "Coverage{" +
                "id=" + id +
                ", contestId=" + contestId +
                ", questionId=" + questionId +
                ", masterId=" + masterId +
                ", masterCodeId=" + masterCodeId +
                ", guestId=" + guestId +
                ", guestCodeId=" + guestCodeId +
                ", coverageRate=" + coverageRate +
                ", createBy=" + createBy +
                ", createTime=" + createTime +
                ", updateBy=" + updateBy +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", delFlag=" + delFlag +
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

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getMasterId() {
        return masterId;
    }

    public void setMasterId(Long masterId) {
        this.masterId = masterId;
    }

    public Long getMasterCodeId() {
        return masterCodeId;
    }

    public void setMasterCodeId(Long masterCodeId) {
        this.masterCodeId = masterCodeId;
    }

    public Long getGuestId() {
        return guestId;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }

    public Long getGuestCodeId() {
        return guestCodeId;
    }

    public void setGuestCodeId(Long guestCodeId) {
        this.guestCodeId = guestCodeId;
    }

    public Double getCoverageRate() {
        return coverageRate;
    }

    public void setCoverageRate(Double coverageRate) {
        this.coverageRate = coverageRate;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Coverage() {
    }

    public Coverage(Long id, Long contestId, Long questionId, Long masterId, Long masterCodeId, Long guestId, Long guestCodeId, Double coverageRate, Long createBy, Date createTime, Long updateBy, Date updateTime, Integer status, Integer delFlag) {
        this.id = id;
        this.contestId = contestId;
        this.questionId = questionId;
        this.masterId = masterId;
        this.masterCodeId = masterCodeId;
        this.guestId = guestId;
        this.guestCodeId = guestCodeId;
        this.coverageRate = coverageRate;
        this.createBy = createBy;
        this.createTime = createTime;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
        this.status = status;
        this.delFlag = delFlag;
    }
}
