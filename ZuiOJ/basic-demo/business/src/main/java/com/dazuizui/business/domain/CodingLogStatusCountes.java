package com.dazuizui.business.domain;

import java.io.Serializable;

public class CodingLogStatusCountes implements Serializable {
    private String status;
    private int count;

    @Override
    public String toString() {
        return "CodingLogStatusCountes{" +
                "status='" + status + '\'' +
                ", count=" + count +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public CodingLogStatusCountes() {
    }

    public CodingLogStatusCountes(String status, int count) {
        this.status = status;
        this.count = count;
    }
}
