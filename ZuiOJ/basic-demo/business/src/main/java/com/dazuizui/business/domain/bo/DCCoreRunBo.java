package com.dazuizui.business.domain.bo;

import java.io.Serializable;

public class DCCoreRunBo implements Serializable {
    private String master;          //主人
    private String guest;           //客
    private Double coverage;        //覆盖率
    private Long masterCodeId;      //主人代码id
    private Long guestCodeId;       //仆人代码id
    private Long masterId;          //主人用户id
    private Long guestId;           //客人用户id


    @Override
    public String toString() {
        return "DCCoreRunBo{" +
                "master='" + master + '\'' +
                ", guest='" + guest + '\'' +
                ", coverage=" + coverage +
                ", masterCodeId=" + masterCodeId +
                ", guestCodeId=" + guestCodeId +
                ", masterId=" + masterId +
                ", guestId=" + guestId +
                '}';
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getGuest() {
        return guest;
    }

    public void setGuest(String guest) {
        this.guest = guest;
    }

    public Double getCoverage() {
        return coverage;
    }

    public void setCoverage(Double coverage) {
        this.coverage = coverage;
    }

    public Long getMasterCodeId() {
        return masterCodeId;
    }

    public void setMasterCodeId(Long masterCodeId) {
        this.masterCodeId = masterCodeId;
    }

    public Long getGuestCodeId() {
        return guestCodeId;
    }

    public void setGuestCodeId(Long guestCodeId) {
        this.guestCodeId = guestCodeId;
    }

    public Long getMasterId() {
        return masterId;
    }

    public void setMasterId(Long masterId) {
        this.masterId = masterId;
    }

    public Long getGuestId() {
        return guestId;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }

    public DCCoreRunBo() {
    }

    public DCCoreRunBo(String master, String guest, Double coverage, Long masterCodeId, Long guestCodeId, Long masterId, Long guestId) {
        this.master = master;
        this.guest = guest;
        this.coverage = coverage;
        this.masterCodeId = masterCodeId;
        this.guestCodeId = guestCodeId;
        this.masterId = masterId;
        this.guestId = guestId;
    }
}
