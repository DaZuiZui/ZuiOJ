package com.dazuizui.basicapi.entry.bo;

import java.io.Serializable;

/**
 * 分页获取博文数据Vo
 */
public class GetBlogPostsByPageBo implements Serializable {
    private Integer status;     //状态
    private Long  start;        //起始位置
    private Long number;        //页数
    private Integer delFlag;    //逻辑删除

    @Override
    public String toString() {
        return "GetBlogPostsByPageBo{" +
                "status=" + status +
                ", start=" + start +
                ", number=" + number +
                ", delFlag=" + delFlag +
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

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public GetBlogPostsByPageBo() {
    }

    public GetBlogPostsByPageBo(Integer status, Long start, Long number, Integer delFlag) {
        this.status = status;
        this.start = start;
        this.number = number;
        this.delFlag = delFlag;
    }
}
