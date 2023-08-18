package com.dazuizui.business.domain.bo;

import java.io.Serializable;

/**
 * 删除友情链接Bo
 */
public class DeleteBlogRollBo implements Serializable {
    private String token;
    private Long id;

    @Override
    public String toString() {
        return "DeleteBlogRollBo{" +
                "token='" + token + '\'' +
                ", id=" + id +
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

    public DeleteBlogRollBo() {
    }

    public DeleteBlogRollBo(String token, Long id) {
        this.token = token;
        this.id = id;
    }
}
