package com.dazuizui.basicapi.entry.vo;

import java.util.Date;

public class ContestQuestionVo {
    private long id ;
    private String shortName;
    private String englishName;
    private String chineseName;
    private long grade;
    private String createByName;
    private Date createTime;
    private long updateById;
    private String updateByName;
    private Date updateTime;
    private int status;
    private int delFlag;
    private int questionType;
    private long createById;
    private Date firstAc;
    private int  numberOfAttempts;

    @Override
    public String toString() {
        return "ContestQuestionVo{" +
                "id=" + id +
                ", shortName='" + shortName + '\'' +
                ", englishName='" + englishName + '\'' +
                ", chineseName='" + chineseName + '\'' +
                ", grade=" + grade +
                ", createByName='" + createByName + '\'' +
                ", createTime=" + createTime +
                ", updateById=" + updateById +
                ", updateByName='" + updateByName + '\'' +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", delFlag=" + delFlag +
                ", questionType=" + questionType +
                ", createById=" + createById +
                ", firstAc=" + firstAc +
                ", numberOfAttempts=" + numberOfAttempts +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public long getGrade() {
        return grade;
    }

    public void setGrade(long grade) {
        this.grade = grade;
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

    public long getUpdateById() {
        return updateById;
    }

    public void setUpdateById(long updateById) {
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

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    public long getCreateById() {
        return createById;
    }

    public void setCreateById(long createById) {
        this.createById = createById;
    }

    public Date getFirstAc() {
        return firstAc;
    }

    public void setFirstAc(Date firstAc) {
        this.firstAc = firstAc;
    }

    public int getNumberOfAttempts() {
        return numberOfAttempts;
    }

    public void setNumberOfAttempts(int numberOfAttempts) {
        this.numberOfAttempts = numberOfAttempts;
    }

    public ContestQuestionVo() {
    }

    public ContestQuestionVo(long id, String shortName, String englishName, String chineseName, long grade, String createByName, Date createTime, long updateById, String updateByName, Date updateTime, int status, int delFlag, int questionType, long createById, Date firstAc, int numberOfAttempts) {
        this.id = id;
        this.shortName = shortName;
        this.englishName = englishName;
        this.chineseName = chineseName;
        this.grade = grade;
        this.createByName = createByName;
        this.createTime = createTime;
        this.updateById = updateById;
        this.updateByName = updateByName;
        this.updateTime = updateTime;
        this.status = status;
        this.delFlag = delFlag;
        this.questionType = questionType;
        this.createById = createById;
        this.firstAc = firstAc;
        this.numberOfAttempts = numberOfAttempts;
    }
}
