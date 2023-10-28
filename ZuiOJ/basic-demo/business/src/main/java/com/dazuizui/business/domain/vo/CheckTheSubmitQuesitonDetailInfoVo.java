package com.dazuizui.business.domain.vo;

import java.io.Serializable;
import java.util.Date;

public class CheckTheSubmitQuesitonDetailInfoVo implements Serializable {
    private String questionName;
    private Long userId;
    private Long numberOfAttempts;
    private Date firstAc;
    private String createByName;
    private Date createTime;
    private Integer status;


    @Override
    public String toString() {
        return "CheckTheSubmitQuesitonDetailInfoVo{" +
                "questionName='" + questionName + '\'' +
                ", userId=" + userId +
                ", numberOfAttempts=" + numberOfAttempts +
                ", firstAc=" + firstAc +
                ", createByName='" + createByName + '\'' +
                ", createTime=" + createTime +
                ", status=" + status +
                '}';
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getNumberOfAttempts() {
        return numberOfAttempts;
    }

    public void setNumberOfAttempts(Long numberOfAttempts) {
        this.numberOfAttempts = numberOfAttempts;
    }

    public Date getFirstAc() {
        return firstAc;
    }

    public void setFirstAc(Date firstAc) {
        this.firstAc = firstAc;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
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

    public CheckTheSubmitQuesitonDetailInfoVo() {
    }

    public CheckTheSubmitQuesitonDetailInfoVo(String questionName, Long userId, Long numberOfAttempts, Date firstAc, String createByName, Date createTime, Integer status) {
        this.questionName = questionName;
        this.userId = userId;
        this.numberOfAttempts = numberOfAttempts;
        this.firstAc = firstAc;
        this.createByName = createByName;
        this.createTime = createTime;
        this.status = status;
    }
}
