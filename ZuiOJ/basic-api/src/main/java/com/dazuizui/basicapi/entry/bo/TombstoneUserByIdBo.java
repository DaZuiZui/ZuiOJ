package com.dazuizui.basicapi.entry.bo;

import java.io.Serializable;

/**
 * 通过id逻辑删除用户Bo
 */
public class TombstoneUserByIdBo implements Serializable {
    private String token;
    private Long   idOfUser;

    @Override
    public String toString() {
        return "TombstoneUserByIdBo{" +
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

    public TombstoneUserByIdBo() {
    }

    public TombstoneUserByIdBo(String token, Long idOfUser) {
        this.token = token;
        this.idOfUser = idOfUser;
    }
}
