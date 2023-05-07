package com.dazuizui.business.domain.bo;

import java.io.Serializable;

/**
 * 管理员删除监考人员通过监考人员ID Bo
 */
public class AdminDeleteProctorByIdBo implements Serializable {
    private String token;      //token
    private Long   proctorId;  //管理人员id
    private Long contestId;    //比赛id
    private Long userId;       //用户id

    @Override
    public String toString() {
        return "AdminDeleteProctorByIdBo{" +
                "token='" + token + '\'' +
                ", proctorId=" + proctorId +
                ", contestId=" + contestId +
                ", userId=" + userId +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getProctorId() {
        return proctorId;
    }

    public void setProctorId(Long proctorId) {
        this.proctorId = proctorId;
    }

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public AdminDeleteProctorByIdBo() {
    }

    public AdminDeleteProctorByIdBo(String token, Long proctorId, Long contestId, Long userId) {
        this.token = token;
        this.proctorId = proctorId;
        this.contestId = contestId;
        this.userId = userId;
    }
}
