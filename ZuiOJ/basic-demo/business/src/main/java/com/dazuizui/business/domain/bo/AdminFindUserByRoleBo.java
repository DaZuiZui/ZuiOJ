package com.dazuizui.business.domain.bo;

import java.io.Serializable;

public class AdminFindUserByRoleBo implements Serializable {
    private String token;
    private Integer role;
    private Long start;
    private Integer size;
    private Integer status;
    private Integer delFlag;

    @Override
    public String toString() {
        return "AdminFindUserByRoleBo{" +
                "token='" + token + '\'' +
                ", role=" + role +
                ", start=" + start +
                ", size=" + size +
                ", status=" + status +
                ", delFlag=" + delFlag +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
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

    public AdminFindUserByRoleBo() {
    }

    public AdminFindUserByRoleBo(String token, Integer role, Long start, Integer size, Integer status, Integer delFlag) {
        this.token = token;
        this.role = role;
        this.start = start;
        this.size = size;
        this.status = status;
        this.delFlag = delFlag;
    }
}
