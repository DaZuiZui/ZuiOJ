package com.dazuizui.basicapi.entry;

import java.io.Serializable;
import java.util.Date;

/**
 * 问题案例
 */
public class QuestionCase implements Serializable {
    private long id;
    private long questionId;
    private String inputs;
    private String answer;
    private String createByName;
    private long   createById;
    private Date createTime;
    private long updateById;
    private String updateByName;
    private Date updateTime;
    private int status;
    private int delFlag;

    @Override
    public String toString() {
        return "QuestionCase{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", inputs='" + inputs + '\'' +
                ", answer='" + answer + '\'' +
                ", createByName='" + createByName + '\'' +
                ", createById=" + createById +
                ", createTime=" + createTime +
                ", updateById=" + updateById +
                ", updateByName='" + updateByName + '\'' +
                ", updateTime=" + updateTime +
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

    public String getInputs() {
        return inputs;
    }

    public void setInputs(String inputs) {
        this.inputs = inputs;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public long getCreateById() {
        return createById;
    }

    public void setCreateById(long createById) {
        this.createById = createById;
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

    public QuestionCase() {
    }

    public QuestionCase(long id, long questionId, String inputs, String answer, String createByName, long createById, Date createTime, long updateById, String updateByName, Date updateTime, int status, int delFlag) {
        this.id = id;
        this.questionId = questionId;
        this.inputs = inputs;
        this.answer = answer;
        this.createByName = createByName;
        this.createById = createById;
        this.createTime = createTime;
        this.updateById = updateById;
        this.updateByName = updateByName;
        this.updateTime = updateTime;
        this.status = status;
        this.delFlag = delFlag;
    }
}
