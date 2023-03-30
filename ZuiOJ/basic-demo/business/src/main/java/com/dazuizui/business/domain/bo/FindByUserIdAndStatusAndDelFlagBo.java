package com.dazuizui.business.domain.bo;

import java.io.Serializable;

/**
 * find invigilator by user id and status and del flag bo
 */
public class FindByUserIdAndStatusAndDelFlagBo implements Serializable {
    private Long userId;
    private String token;
    private Integer status;
    private Integer delFlag;

    @Override
    public String toString() {
        return "FindByUserIdAndStatusAndDelFlagBo{" +
                "userId=" + userId +
                ", token='" + token + '\'' +
                ", status=" + status +
                ", delFlag=" + delFlag +
                '}';
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public FindByUserIdAndStatusAndDelFlagBo() {
    }

    public FindByUserIdAndStatusAndDelFlagBo(Long userId, String token, Integer status, Integer delFlag) {
        this.userId = userId;
        this.token = token;
        this.status = status;
        this.delFlag = delFlag;
    }
}
