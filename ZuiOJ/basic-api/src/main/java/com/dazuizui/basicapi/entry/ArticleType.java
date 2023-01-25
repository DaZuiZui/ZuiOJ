package com.dazuizui.basicapi.entry;

import java.io.Serializable;

/**
 * 文章分类
 */
public class ArticleType implements Serializable {
    private Long id;
    private Long article_id;
    private Long article_type;

    @Override
    public String toString() {
        return "ArticleType{" +
                "id=" + id +
                ", article_id=" + article_id +
                ", article_type=" + article_type +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Long article_id) {
        this.article_id = article_id;
    }

    public Long getArticle_type() {
        return article_type;
    }

    public void setArticle_type(Long article_type) {
        this.article_type = article_type;
    }

    public ArticleType() {
    }

    public ArticleType(Long id, Long article_id, Long article_type) {
        this.id = id;
        this.article_id = article_id;
        this.article_type = article_type;
    }
}
