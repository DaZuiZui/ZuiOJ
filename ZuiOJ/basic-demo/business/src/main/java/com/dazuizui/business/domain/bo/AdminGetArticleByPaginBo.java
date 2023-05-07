package com.dazuizui.business.domain.bo;

import java.io.Serializable;

/**
 * 管理员获取文章分页Bo
 */
public class AdminGetArticleByPaginBo implements Serializable {
    private String token;
    private Long start;
    private Long size;
    private Integer status;
    private Integer delFlag;

    @Override
    public String toString() {
        return "AdminGetArticleByPaginBo{" +
                "token='" + token + '\'' +
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

    public AdminGetArticleByPaginBo() {
    }

    public AdminGetArticleByPaginBo(String token, Long start, Long size, Integer status, Integer delFlag) {
        this.token = token;
        this.start = start;
        this.size = size;
        this.status = status;
        this.delFlag = delFlag;
    }
}
