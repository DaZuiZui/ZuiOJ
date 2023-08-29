package com.dazuizui.business.domain.vo;

import com.dazuizui.business.domain.CodeInContest;

import java.io.Serializable;

public class DuplicateCodeVo implements Serializable {
    private String guestCode;
    private String masterCode;
    private Long guestId;
    private Long masterId;


    @Override
    public String toString() {
        return "DuplicateCodeVo{" +
                "guestCode='" + guestCode + '\'' +
                ", masterCode='" + masterCode + '\'' +
                ", guestId=" + guestId +
                ", masterId=" + masterId +
                '}';
    }

    public String getGuestCode() {
        return guestCode;
    }

    public void setGuestCode(String guestCode) {
        this.guestCode = guestCode;
    }

    public String getMasterCode() {
        return masterCode;
    }

    public void setMasterCode(String masterCode) {
        this.masterCode = masterCode;
    }

    public Long getGuestId() {
        return guestId;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }

    public Long getMasterId() {
        return masterId;
    }

    public void setMasterId(Long masterId) {
        this.masterId = masterId;
    }

    public DuplicateCodeVo() {
    }

    public DuplicateCodeVo(String guestCode, String masterCode, Long guestId, Long masterId) {
        this.guestCode = guestCode;
        this.masterCode = masterCode;
        this.guestId = guestId;
        this.masterId = masterId;
    }
}
