package com.dazuizui.basicapi.entry;

import java.io.Serializable;
import java.util.Date;

/**
 * 问题资源的限制
 */
public class ProblemLimit implements Serializable {
    private long id;
    private long questionId;
    private int  stdoutMax;
    private int  stderrMax;
    private int  cpuLimit;
    private int memoryLimit;
    private int procLimit;
    private long createById;
    private String createByName;
    private Date   createTime;
    private Date   updateTime;
    private String updateByName;
    private long   updateById;
    private int status;
    private int delFlag;

    @Override
    public String toString() {
        return "ProblemLimit{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", stdoutMax=" + stdoutMax +
                ", stderrMax=" + stderrMax +
                ", cpuLimit=" + cpuLimit +
                ", memoryLimit=" + memoryLimit +
                ", procLimit=" + procLimit +
                ", createById=" + createById +
                ", createByName='" + createByName + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", updateByName='" + updateByName + '\'' +
                ", updateById=" + updateById +
                ", status=" + status +
                ", delFlag=" + delFlag +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public int getStdoutMax() {
        return stdoutMax;
    }

    public void setStdoutMax(int stdoutMax) {
        this.stdoutMax = stdoutMax;
    }

    public int getStderrMax() {
        return stderrMax;
    }

    public void setStderrMax(int stderrMax) {
        this.stderrMax = stderrMax;
    }

    public int getCpuLimit() {
        return cpuLimit;
    }

    public void setCpuLimit(int cpuLimit) {
        this.cpuLimit = cpuLimit;
    }

    public int getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(int memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public int getProcLimit() {
        return procLimit;
    }

    public void setProcLimit(int procLimit) {
        this.procLimit = procLimit;
    }

    public long getCreateById() {
        return createById;
    }

    public void setCreateById(long createById) {
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateByName() {
        return updateByName;
    }

    public void setUpdateByName(String updateByName) {
        this.updateByName = updateByName;
    }

    public long getUpdateById() {
        return updateById;
    }

    public void setUpdateById(long updateById) {
        this.updateById = updateById;
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

    public ProblemLimit() {
    }

    public ProblemLimit(long id, long questionId, int stdoutMax, int stderrMax, int cpuLimit, int memoryLimit, int procLimit, long createById, String createByName, Date createTime, Date updateTime, String updateByName, long updateById, int status, int delFlag) {
        this.id = id;
        this.questionId = questionId;
        this.stdoutMax = stdoutMax;
        this.stderrMax = stderrMax;
        this.cpuLimit = cpuLimit;
        this.memoryLimit = memoryLimit;
        this.procLimit = procLimit;
        this.createById = createById;
        this.createByName = createByName;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.updateByName = updateByName;
        this.updateById = updateById;
        this.status = status;
        this.delFlag = delFlag;
    }
}
