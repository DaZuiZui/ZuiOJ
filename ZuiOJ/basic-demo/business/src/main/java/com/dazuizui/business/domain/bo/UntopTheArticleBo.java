package com.dazuizui.business.domain.bo;

import java.io.Serializable;

public class UntopTheArticleBo implements Serializable {
    private Long id;
    private String token;

    @Override
    public String toString() {
        return "UntopTheArticleBo{" +
                "id=" + id +
                ", token='" + token + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UntopTheArticleBo() {
    }

    public UntopTheArticleBo(Long id, String token) {
        this.id = id;
        this.token = token;
    }
}
