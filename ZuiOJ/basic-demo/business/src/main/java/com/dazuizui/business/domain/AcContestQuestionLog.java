package com.dazuizui.business.domain;

import java.io.Serializable;
import java.util.Date;

public class AcContestQuestionLog implements Serializable {
    private Long id;
    private Long questionId;
    private Integer acStatus;
    private Long createBy;
    private Date createTime;
    private Long updateBy;
    private Date updateTime;
    private Integer status;
    private Integer delFlag;

    @Override
    public String toString() {
        return "AcContestQuestionLogVo{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", acStatus=" + acStatus +
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

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Integer getAcStatus() {
        return acStatus;
    }

    public void setAcStatus(Integer acStatus) {
        this.acStatus = acStatus;
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

    public AcContestQuestionLog() {
    }

    public AcContestQuestionLog(Long id, Long questionId, Integer acStatus, Long createBy, Date createTime, Long updateBy, Date updateTime, Integer status, Integer delFlag) {
        this.id = id;
        this.questionId = questionId;
        this.acStatus = acStatus;
        this.createBy = createBy;
        this.createTime = createTime;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
        this.status = status;
        this.delFlag = delFlag;
    }
}
