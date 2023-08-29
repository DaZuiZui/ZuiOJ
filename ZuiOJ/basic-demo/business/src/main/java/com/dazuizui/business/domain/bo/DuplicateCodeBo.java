package com.dazuizui.business.domain.bo;

import java.io.Serializable;

public class DuplicateCodeBo implements Serializable {
    private Long masterCodeId;
    private Long masterId;
    private Long guestCodeId;
    private Long guestId;
    private String token;

    @Override
    public String toString() {
        return "DuplicateCodeBo{" +
                "masterCodeId=" + masterCodeId +
                ", masterId=" + masterId +
                ", guestCodeId=" + guestCodeId +
                ", guestId=" + guestId +
                ", token='" + token + '\'' +
                '}';
    }

    public Long getMasterCodeId() {
        return masterCodeId;
    }

    public void setMasterCodeId(Long masterCodeId) {
        this.masterCodeId = masterCodeId;
    }

    public Long getMasterId() {
        return masterId;
    }

    public void setMasterId(Long masterId) {
        this.masterId = masterId;
    }

    public Long getGuestCodeId() {
        return guestCodeId;
    }

    public void setGuestCodeId(Long guestCodeId) {
        this.guestCodeId = guestCodeId;
    }

    public Long getGuestId() {
        return guestId;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public DuplicateCodeBo() {
    }

    public DuplicateCodeBo(Long masterCodeId, Long masterId, Long guestCodeId, Long guestId, String token) {
        this.masterCodeId = masterCodeId;
        this.masterId = masterId;
        this.guestCodeId = guestCodeId;
        this.guestId = guestId;
        this.token = token;
    }
}

