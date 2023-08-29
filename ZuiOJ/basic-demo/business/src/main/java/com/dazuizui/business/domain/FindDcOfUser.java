package com.dazuizui.business.domain;

import java.io.Serializable;
import java.util.Date;

public class FindDcOfUser implements Serializable {
    private Long id;
    private Long masterId;
    private Long guestId;
    private Long masterCodeId;
    private Long guestCodeId;
    private Date createTime;
    private String masterName;
    private String guestName;
    private String questionName;
    private Double coverageRate;

    @Override
    public String toString() {
        return "FindDcOfUser{" +
                "id=" + id +
                ", masterId=" + masterId +
                ", guestId=" + guestId +
                ", masterCodeId=" + masterCodeId +
                ", guestCodeId=" + guestCodeId +
                ", createTime=" + createTime +
                ", masterName='" + masterName + '\'' +
                ", guestName='" + guestName + '\'' +
                ", questionName='" + questionName + '\'' +
                ", coverageRate=" + coverageRate +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMasterId() {
        return masterId;
    }

    public void setMasterId(Long masterId) {
        this.masterId = masterId;
    }

    public Long getGuestId() {
        return guestId;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }

    public Long getMasterCodeId() {
        return masterCodeId;
    }

    public void setMasterCodeId(Long masterCodeId) {
        this.masterCodeId = masterCodeId;
    }

    public Long getGuestCodeId() {
        return guestCodeId;
    }

    public void setGuestCodeId(Long guestCodeId) {
        this.guestCodeId = guestCodeId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public Double getCoverageRate() {
        return coverageRate;
    }

    public void setCoverageRate(Double coverageRate) {
        this.coverageRate = coverageRate;
    }

    public FindDcOfUser() {
    }

    public FindDcOfUser(Long id, Long masterId, Long guestId, Long masterCodeId, Long guestCodeId, Date createTime, String masterName, String guestName, String questionName, Double coverageRate) {
        this.id = id;
        this.masterId = masterId;
        this.guestId = guestId;
        this.masterCodeId = masterCodeId;
        this.guestCodeId = guestCodeId;
        this.createTime = createTime;
        this.masterName = masterName;
        this.guestName = guestName;
        this.questionName = questionName;
        this.coverageRate = coverageRate;
    }
}
