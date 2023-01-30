package com.dazuizui.basicapi.entry.bo;

import java.io.Serializable;

/**
 * 通过id获取博文
 */
public class GetArticleByIdBo implements Serializable {
    private Long id;         //博文id
    private String token;    //token
    private String mdTextId; //md文档id

    @Override
    public String toString() {
        return "GetArticleByIdBo{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", mdTextId='" + mdTextId + '\'' +
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

    public String getMdTextId() {
        return mdTextId;
    }

    public void setMdTextId(String mdTextId) {
        this.mdTextId = mdTextId;
    }

    public GetArticleByIdBo() {
    }

    public GetArticleByIdBo(Long id, String token, String mdTextId) {
        this.id = id;
        this.token = token;
        this.mdTextId = mdTextId;
    }
}
