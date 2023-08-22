package com.dazuizui.business.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 团队成员实体
 */
public class OrgMember implements Serializable {
    private Long id;
    private String name;
    private String numberId;
    private Long ordId;
    private Long preOrg;
    private Long currentOrg;
    private Long createBy;
    private Date createTime;
    private Long updateBy;
    private Date updateTime;
    private Integer status;
    private Integer delFlag;
    private String email;

    @Override
    public String toString() {
        return "OrgMember{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberId='" + numberId + '\'' +
                ", ordId=" + ordId +
                ", preOrg=" + preOrg +
                ", currentOrg=" + currentOrg +
                ", createBy=" + createBy +
                ", createTime=" + createTime +
                ", updateBy=" + updateBy +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", delFlag=" + delFlag +
                ", email='" + email + '\'' +
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

    public String getNumberId() {
        return numberId;
    }

    public void setNumberId(String numberId) {
        this.numberId = numberId;
    }

    public Long getOrdId() {
        return ordId;
    }

    public void setOrdId(Long ordId) {
        this.ordId = ordId;
    }

    public Long getPreOrg() {
        return preOrg;
    }

    public void setPreOrg(Long preOrg) {
        this.preOrg = preOrg;
    }

    public Long getCurrentOrg() {
        return currentOrg;
    }

    public void setCurrentOrg(Long currentOrg) {
        this.currentOrg = currentOrg;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public OrgMember() {
    }

    public OrgMember(Long id, String name, String numberId, Long ordId, Long preOrg, Long currentOrg, Long createBy, Date createTime, Long updateBy, Date updateTime, Integer status, Integer delFlag, String email) {
        this.id = id;
        this.name = name;
        this.numberId = numberId;
        this.ordId = ordId;
        this.preOrg = preOrg;
        this.currentOrg = currentOrg;
        this.createBy = createBy;
        this.createTime = createTime;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
        this.status = status;
        this.delFlag = delFlag;
        this.email = email;
    }
}
