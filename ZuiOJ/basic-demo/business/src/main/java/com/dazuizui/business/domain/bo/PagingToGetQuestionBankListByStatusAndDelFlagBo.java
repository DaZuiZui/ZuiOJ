package com.dazuizui.business.domain.bo;

import java.io.Serializable;

/**
 * 分页获取题库通过Status 和 DelFLag
 */
public class PagingToGetQuestionBankListByStatusAndDelFlagBo implements Serializable {
    private String token;
    private Long status;
    private Integer number;
    private Integer start;
    private Integer delFlag;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return "PagingToGetQuestionBankListByStatusAndDelFlagBo{" +
                "token='" + token + '\'' +
                ", status=" + status +
                ", number=" + number +
                ", start=" + start +
                ", delFlag=" + delFlag +
                '}';
    }

    public PagingToGetQuestionBankListByStatusAndDelFlagBo() {
    }

    public PagingToGetQuestionBankListByStatusAndDelFlagBo(String token, Long status, Integer number, Integer start, Integer delFlag) {
        this.token = token;
        this.status = status;
        this.number = number;
        this.start = start;
        this.delFlag = delFlag;
    }
}
