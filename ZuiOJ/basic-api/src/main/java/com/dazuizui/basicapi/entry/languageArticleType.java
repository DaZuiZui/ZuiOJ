package com.dazuizui.basicapi.entry;

import java.io.Serializable;

/**
 * 编程语言博文分类
 */
public class languageArticleType implements Serializable {
    private Long id;
    private Long languageType;
    private Long articleId;

    @Override
    public String toString() {
        return "languageArticleType{" +
                "id=" + id +
                ", languageType=" + languageType +
                ", articleId=" + articleId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLanguageType() {
        return languageType;
    }

    public void setLanguageType(Long languageType) {
        this.languageType = languageType;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public languageArticleType() {
    }

    public languageArticleType(Long id, Long languageType, Long articleId) {
        this.id = id;
        this.languageType = languageType;
        this.articleId = articleId;
    }
}
