package com.dazuizui.business.domain.bo;

import java.io.Serializable;

public class ProctorGetLastEventsInfoBo implements Serializable {
    private String proctorToken;
    private Long start;
    private Long size;
    private String token;


    @Override
    public String toString() {
        return "ProctorGetFutureEventsInfoBo{" +
                "proctorToken='" + proctorToken + '\'' +
                ", start=" + start +
                ", size=" + size +
                ", token='" + token + '\'' +
                '}';
    }

    public String getProctorToken() {
        return proctorToken;
    }

    public void setProctorToken(String proctorToken) {
        this.proctorToken = proctorToken;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ProctorGetLastEventsInfoBo() {
    }

    public ProctorGetLastEventsInfoBo(String proctorToken, Long start, Long size, String token) {
        this.proctorToken = proctorToken;
        this.start = start;
        this.size = size;
        this.token = token;
    }
}
