package com.dazuizui.business.domain.bo;

import java.io.Serializable;

/**
 * 通过元素查询日记
 */
public class ElementOfQueryLogBo implements Serializable {
    private String name;    //用户名
    private Long questionId;//问题id
    private Long startnum;  //开始页数
    private Long endnum;    //结束页数
    private Integer status; //状态
    private String token;   //token
    private Long contestId; //比赛id

    @Override
    public String toString() {
        return "ElementOfQueryLogBo{" +
                "name='" + name + '\'' +
                ", questionId=" + questionId +
                ", startnum=" + startnum +
                ", endnum=" + endnum +
                ", status=" + status +
                ", token='" + token + '\'' +
                ", contestId=" + contestId +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getStartnum() {
        return startnum;
    }

    public void setStartnum(Long startnum) {
        this.startnum = startnum;
    }

    public Long getEndnum() {
        return endnum;
    }

    public void setEndnum(Long endnum) {
        this.endnum = endnum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    public ElementOfQueryLogBo() {
    }

    public ElementOfQueryLogBo(String name, Long questionId, Long startnum, Long endnum, Integer status, String token, Long contestId) {
        this.name = name;
        this.questionId = questionId;
        this.startnum = startnum;
        this.endnum = endnum;
        this.status = status;
        this.token = token;
        this.contestId = contestId;
    }
}
