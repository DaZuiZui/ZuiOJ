package com.dazuizui.business.domain.vo;

import com.dazuizui.business.domain.Article;

import java.io.Serializable;
import java.util.List;

/**
 * 管理员分页获取博文VO
 */
public class AdminGetArticleByPaginVo implements Serializable {
    private Long count;
    private List<Article> articleList;

    @Override
    public String toString() {
        return "AdminGetArticleByPaginVo{" +
                "count=" + count +
                ", articleList=" + articleList +
                '}';
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public AdminGetArticleByPaginVo() {
    }

    public AdminGetArticleByPaginVo(Long count, List<Article> articleList) {
        this.count = count;
        this.articleList = articleList;
    }
}
