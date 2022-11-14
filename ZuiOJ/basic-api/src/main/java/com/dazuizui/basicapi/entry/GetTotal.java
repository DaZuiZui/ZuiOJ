package com.dazuizui.basicapi.entry;

import java.io.Serializable;
import java.util.Date;

public class GetTotal implements Serializable {
    private Long count;
    private Date startTime;

    @Override
    public String toString() {
        return "GetTotal{" +
                "count=" + count +
                ", startTime=" + startTime +
                '}';
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public GetTotal() {
    }

    public GetTotal(Long count, Date startTime) {
        this.count = count;
        this.startTime = startTime;
    }
}
