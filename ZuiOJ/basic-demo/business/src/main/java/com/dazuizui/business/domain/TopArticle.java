package com.dazuizui.business.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 置顶文章实体
 */
public class TopArticle implements Serializable {
    private Long id;
    private Long articleId;
    private Long createBy;
    private Date createTime;

    @Override
    public String toString() {
        return "TopArticle{" +
                "id=" + id +
                ", articleId=" + articleId +
                ", createBy=" + createBy +
                ", createTime=" + createTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public TopArticle() {
    }

    public TopArticle(Long id, Long articleId, Long createBy, Date createTime) {
        this.id = id;
        this.articleId = articleId;
        this.createBy = createBy;
        this.createTime = createTime;
    }
}
