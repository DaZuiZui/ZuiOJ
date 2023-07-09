package com.dazuizui.business.domain.vo;

import java.io.Serializable;

/**
 * 查看自己发布的博文VO
 */
public class GetMyArticleVo implements Serializable {
    private String token;
    private Long start;
    private Long size;

    @Override
    public String toString() {
        return "GetMyArticleVo{" +
                "token='" + token + '\'' +
                ", start=" + start +
                ", size=" + size +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public GetMyArticleVo() {
    }

    public GetMyArticleVo(String token, Long start, Long size) {
        this.token = token;
        this.start = start;
        this.size = size;
    }
}
