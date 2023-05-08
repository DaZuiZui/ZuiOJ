package com.dazuizui.business.domain.bo;

import java.io.Serializable;

/**
 * 管理员删除博文通过id
 */
public class AdminDeleteAritcleByIdBo implements Serializable {
    private String token;
    private Long id;
    private Integer status;


    @Override
    public String toString() {
        return "AdminDeleteAritcleByIdBo{" +
                "token='" + token + '\'' +
                ", id=" + id +
                ", status=" + status +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public AdminDeleteAritcleByIdBo() {
    }

    public AdminDeleteAritcleByIdBo(String token, Long id, Integer status) {
        this.token = token;
        this.id = id;
        this.status = status;
    }
}
