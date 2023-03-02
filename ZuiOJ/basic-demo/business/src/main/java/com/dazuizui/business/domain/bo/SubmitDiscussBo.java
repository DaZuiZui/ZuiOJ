package com.dazuizui.business.domain.bo;

import com.dazuizui.business.domain.QuestionDiscuss;

import java.io.Serializable;

/**
 * 提交评论BO
 */
public class SubmitDiscussBo implements Serializable {
    //讨论内容
    private QuestionDiscuss discuss;
    //token
    private String token;

    @Override
    public String toString() {
        return "SubmitDiscussBo{" +
                "discuss=" + discuss +
                ", token='" + token + '\'' +
                ", nonPowerToken='" + nonPowerToken + '\'' +
                '}';
    }

    public QuestionDiscuss getDiscuss() {
        return discuss;
    }

    public void setDiscuss(QuestionDiscuss discuss) {
        this.discuss = discuss;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNonPowerToken() {
        return nonPowerToken;
    }

    public void setNonPowerToken(String nonPowerToken) {
        this.nonPowerToken = nonPowerToken;
    }

    public SubmitDiscussBo() {
    }

    public SubmitDiscussBo(QuestionDiscuss discuss, String token, String nonPowerToken) {
        this.discuss = discuss;
        this.token = token;
        this.nonPowerToken = nonPowerToken;
    }

    //幂等性
    private String nonPowerToken;
}
