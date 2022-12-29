package com.dazuizui.basicapi.entry.bo;

import java.io.Serializable;

/**
 * 物理删除用户BO
 */
public class DeleteUserByIdBo implements Serializable {
    private String token;
    private Long idOfUser;

    @Override
    public String toString() {
        return "DeleteUserByIdBo{" +
                "token='" + token + '\'' +
                ", idOfUser=" + idOfUser +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getIdOfUser() {
        return idOfUser;
    }

    public void setIdOfUser(Long idOfUser) {
        this.idOfUser = idOfUser;
    }

    public DeleteUserByIdBo() {
    }

    public DeleteUserByIdBo(String token, Long idOfUser) {
        this.token = token;
        this.idOfUser = idOfUser;
    }
}
