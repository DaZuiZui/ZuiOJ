package com.dazuizui.basicapi.entry;

import java.io.Serializable;
import java.util.Date;

/**
 * 编程语言实体类
 */
public class ProgrammingLanguage implements Serializable {
    private  long id;
    private String language;
    private String createByName;
    private long   createById;
    private Date   createTime;
    private long updateById;
    private String updateByName;
    private Date updateTime;
    private int status;
    private int delFlag;

    @Override
    public String toString() {
        return "ProgrammingLanguage{" +
                "id=" + id +
                ", language='" + language + '\'' +
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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

    public ProgrammingLanguage() {
    }

    public ProgrammingLanguage(long id, String language, String createByName, long createById, Date createTime, long updateById, String updateByName, Date updateTime, int status, int delFlag) {
        this.id = id;
        this.language = language;
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
