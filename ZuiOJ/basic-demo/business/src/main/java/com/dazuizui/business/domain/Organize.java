package com.dazuizui.business.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 组织实体
 */
public class Organize implements Serializable {
    private Long id;
    private String name;
    private Long createBy;
    private Date date;
    private Long updateBy;
    private Long updateTime;
    private Integer status;
    private Integer delFlag;

    @Override
    public String toString() {
        return "Organize{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createBy=" + createBy +
                ", date=" + date +
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
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

    public Organize() {
    }

    public Organize(Long id, String name, Long createBy, Date date, Long updateBy, Long updateTime, Integer status, Integer delFlag) {
        this.id = id;
        this.name = name;
        this.createBy = createBy;
        this.date = date;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
        this.status = status;
        this.delFlag = delFlag;
    }
}
