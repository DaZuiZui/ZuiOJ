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

    /**
     * 管理员分页获取比赛信息Bo
     */
    public static class AdminQueryGameInformationByPageVo implements Serializable {
        private String token;
        private Long page;
        private Long size;

        @Override
        public String toString() {
            return "AdminQueryGameInformationByPageVo{" +
                    "token='" + token + '\'' +
                    ", page=" + page +
                    ", size=" + size +
                    '}';
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public Long getPage() {
            return page;
        }

        public void setPage(Long page) {
            this.page = page;
        }

        public Long getSize() {
            return size;
        }

        public void setSize(Long size) {
            this.size = size;
        }

        public AdminQueryGameInformationByPageVo() {
        }

        public AdminQueryGameInformationByPageVo(String token, Long page, Long size) {
            this.token = token;
            this.page = page;
            this.size = size;
        }
    }
}
