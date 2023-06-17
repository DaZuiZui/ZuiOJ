package com.dazuizui.business.domain.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 获取用户发布的博文数量
 */
public class UserGetMyselfArticleVo implements Serializable {
    private Long count;
    private List<ArticleVo> articleVos;

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<ArticleVo> getArticleVos() {
        return articleVos;
    }

    public void setArticleVos(List<ArticleVo> articleVos) {
        this.articleVos = articleVos;
    }

    public UserGetMyselfArticleVo() {
    }

    public UserGetMyselfArticleVo(Long count, List<ArticleVo> articleVos) {
        this.count = count;
        this.articleVos = articleVos;
    }
}
