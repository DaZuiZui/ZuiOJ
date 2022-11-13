package com.dazuizui.basicapi.entry;

import java.io.Serializable;
import java.util.Date;

/**
 * AC比赛题目信息
 */
public class AcContestQuestion implements Serializable {
    private Long id;
    private Long contestId;
    private Long questionId;
    private Long userId;
    private Long createById; //创建者ID
    private String createByName; //创建者name
    private Date createTime; //创建时间
    private Long updateById; //修改者ID
    private String updateByName; //修改者name
    private Date updateTime; //修改时间
    private int status;      //状态
    private int delFlag;     //逻辑删除
    private Integer numberOfAttempts; //尝试次数
    private Date firstAc;             //第一次ac时间

    @Override
    public String toString() {
        return "AcContestQuestion{" +
                "id=" + id +
                ", contestId=" + contestId +
                ", questionId=" + questionId +
                ", userId=" + userId +
                ", createById=" + createById +
                ", createByName='" + createByName + '\'' +
                ", createTime=" + createTime +
                ", updateById=" + updateById +
                ", updateByName='" + updateByName + '\'' +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", delFlag=" + delFlag +
                ", numberOfAttempts=" + numberOfAttempts +
                ", firstAc=" + firstAc +
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCreateById() {
        return createById;
    }

    public void setCreateById(Long createById) {
        this.createById = createById;
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

    public Long getUpdateById() {
        return updateById;
    }

    public void setUpdateById(Long updateById) {
        this.updateById = updateById;
    }

    public String getUpdateByName() {
        return updateByName;
    }

    public void setUpdateByName(String updateByName) {
        this.updateByName = updateByName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getNumberOfAttempts() {
        return numberOfAttempts;
    }

    public void setNumberOfAttempts(Integer numberOfAttempts) {
        this.numberOfAttempts = numberOfAttempts;
    }

    public Date getFirstAc() {
        return firstAc;
    }

    public void setFirstAc(Date firstAc) {
        this.firstAc = firstAc;
    }

    public AcContestQuestion() {
    }

    public AcContestQuestion(Long id, Long contestId, Long questionId, Long userId, Long createById, String createByName, Date createTime, Long updateById, String updateByName, Date updateTime, int status, int delFlag, Integer numberOfAttempts, Date firstAc) {
        this.id = id;
        this.contestId = contestId;
        this.questionId = questionId;
        this.userId = userId;
        this.createById = createById;
        this.createByName = createByName;
        this.createTime = createTime;
        this.updateById = updateById;
        this.updateByName = updateByName;
        this.updateTime = updateTime;
        this.status = status;
        this.delFlag = delFlag;
        this.numberOfAttempts = numberOfAttempts;
        this.firstAc = firstAc;
    }
}
