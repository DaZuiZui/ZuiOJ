package com.dazuizui.basicapi.entry.vo;

import java.io.Serializable;

/**
 * 根据题解贡献数量
 */
public class GetSolutionContributorsVo implements Serializable {
    private Long userId;            //用户id
    private Long countOfArticle;    //题解数量
    private String name;            //用户名

    @Override
    public String toString() {
        return "GetSolutionContributorsVo{" +
                "userId=" + userId +
                ", countOfArticle=" + countOfArticle +
                ", name='" + name + '\'' +
                '}';
    }

    public GetSolutionContributorsVo() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCountOfArticle() {
        return countOfArticle;
    }

    public void setCountOfArticle(Long countOfArticle) {
        this.countOfArticle = countOfArticle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GetSolutionContributorsVo(Long userId, Long countOfArticle, String name) {
        this.userId = userId;
        this.countOfArticle = countOfArticle;
        this.name = name;
    }
}
