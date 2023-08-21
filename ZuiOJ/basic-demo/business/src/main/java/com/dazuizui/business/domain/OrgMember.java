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
    private Long ord_id;
    private Long pre_org;
    private Long current_org;
    private Long createBy;
    private Date createTime;
    private Long updateBy;
    private Date updateTime;
    private Integer status;
    private Integer delFlag;

    @Override
    public String toString() {
        return "OrgMember{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberId='" + numberId + '\'' +
                ", ord_id=" + ord_id +
                ", pre_org=" + pre_org +
                ", current_org=" + current_org +
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

    public Long getOrd_id() {
        return ord_id;
    }

    public void setOrd_id(Long ord_id) {
        this.ord_id = ord_id;
    }

    public Long getPre_org() {
        return pre_org;
    }

    public void setPre_org(Long pre_org) {
        this.pre_org = pre_org;
    }

    public Long getCurrent_org() {
        return current_org;
    }

    public void setCurrent_org(Long current_org) {
        this.current_org = current_org;
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

    public OrgMember() {
    }

    public OrgMember(Long id, String name, String numberId, Long ord_id, Long pre_org, Long current_org, Long createBy, Date createTime, Long updateBy, Date updateTime, Integer status, Integer delFlag) {
        this.id = id;
        this.name = name;
        this.numberId = numberId;
        this.ord_id = ord_id;
        this.pre_org = pre_org;
        this.current_org = current_org;
        this.createBy = createBy;
        this.createTime = createTime;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
        this.status = status;
        this.delFlag = delFlag;
    }
}
