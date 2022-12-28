package com.dazuizui.basicapi.entry.bo;

import java.io.Serializable;

/**
 * 分页获取用户数据Vo
 */
public class PagingToGetUserDateBo implements Serializable {
    private String token;
    private Long pages;
    private Long numbers;

    @Override
    public String toString() {
        return "PagingToGetUserDateBo{" +
                "token='" + token + '\'' +
                ", pages=" + pages +
                ", numbers=" + numbers +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getPages() {
        return pages;
    }

    public void setPages(Long pages) {
        this.pages = pages;
    }

    public Long getNumbers() {
        return numbers;
    }

    public void setNumbers(Long numbers) {
        this.numbers = numbers;
    }

    public PagingToGetUserDateBo() {
    }

    public PagingToGetUserDateBo(String token, Long pages, Long numbers) {
        this.token = token;
        this.pages = pages;
        this.numbers = numbers;
    }
}
