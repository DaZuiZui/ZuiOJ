package com.dazuizui.business.domain.bo;

import java.io.Serializable;

/**
 * 添加置顶文章BO
 */
public class AddTopArticleBo implements Serializable {
    private String token;
    private Long articleId;
    private String nonPowerToken;

    @Override
    public String toString() {
        return "AddTopArticleBo{" +
                "token='" + token + '\'' +
                ", articleId=" + articleId +
                ", nonPowerToken='" + nonPowerToken + '\'' +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getNonPowerToken() {
        return nonPowerToken;
    }

    public void setNonPowerToken(String nonPowerToken) {
        this.nonPowerToken = nonPowerToken;
    }

    public AddTopArticleBo() {
    }

    public AddTopArticleBo(String token, Long articleId, String nonPowerToken) {
        this.token = token;
        this.articleId = articleId;
        this.nonPowerToken = nonPowerToken;
    }
}
