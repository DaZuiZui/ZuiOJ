package com.dazuizui.business.domain.bo;

import java.io.Serializable;

/**
 * 用户获取自己发布的文章
 */
public class UserGetMyselfArticleBo implements Serializable {
    private String token;
    private Integer status;
    private Integer delFlag;
    private Long id;
    private Long start;
    private Long size;

    @Override
    public String toString() {
        return "UserGetMyselfArticleBo{" +
                "token='" + token + '\'' +
                ", status=" + status +
                ", delFlag=" + delFlag +
                ", id=" + id +
                ", start=" + start +
                ", size=" + size +
                '}';
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public UserGetMyselfArticleBo() {
    }

    public UserGetMyselfArticleBo(String token, Integer status, Integer delFlag, Long id, Long start, Long size) {
        this.token = token;
        this.status = status;
        this.delFlag = delFlag;
        this.id = id;
        this.start = start;
        this.size = size;
    }
}
