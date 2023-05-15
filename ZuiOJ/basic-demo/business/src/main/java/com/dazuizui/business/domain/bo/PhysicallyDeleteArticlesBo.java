package com.dazuizui.business.domain.bo;

import java.io.Serializable;
import java.util.List;

/**
 * 管理员批量删除博文
 */
public class PhysicallyDeleteArticlesBo implements Serializable {
    private String token;
    private List<Long> elements;
    private String nonPowerToken;


    @Override
    public String toString() {
        return "PhysicallyDeleteArticlesBo{" +
                "token='" + token + '\'' +
                ", elements=" + elements +
                ", nonPowerToken='" + nonPowerToken + '\'' +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Long> getElements() {
        return elements;
    }

    public void setElements(List<Long> elements) {
        this.elements = elements;
    }

    public String getNonPowerToken() {
        return nonPowerToken;
    }

    public void setNonPowerToken(String nonPowerToken) {
        this.nonPowerToken = nonPowerToken;
    }

    public PhysicallyDeleteArticlesBo() {
    }

    public PhysicallyDeleteArticlesBo(String token, List<Long> elements, String nonPowerToken) {
        this.token = token;
        this.elements = elements;
        this.nonPowerToken = nonPowerToken;
    }
}
