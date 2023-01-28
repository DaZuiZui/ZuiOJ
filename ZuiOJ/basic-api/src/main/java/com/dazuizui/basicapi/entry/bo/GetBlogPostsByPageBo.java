package com.dazuizui.basicapi.entry.bo;

import java.io.Serializable;

/**
 * 分页获取博文数据Vo
 */
public class GetBlogPostsByPageBo implements Serializable {
    private Integer status;     //状态
    private Long  start;        //起始位置
    private Long number;        //页数

    @Override
    public String toString() {
        return "GetBlogPostsByPageBo{" +
                "status=" + status +
                ", start=" + start +
                ", number=" + number +
                '}';
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public GetBlogPostsByPageBo() {
    }

    public GetBlogPostsByPageBo(Integer status, Long start, Long number) {
        this.status = status;
        this.start = start;
        this.number = number;
    }
}
