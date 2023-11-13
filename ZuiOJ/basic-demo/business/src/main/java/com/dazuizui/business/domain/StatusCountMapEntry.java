package com.dazuizui.business.domain;

import java.io.Serializable;

public class StatusCountMapEntry implements Serializable {
    private String key;
    private Integer count;

    @Override
    public String toString() {
        return "StatusCountMapEntry{" +
                "key='" + key + '\'' +
                ", count=" + count +
                '}';
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public StatusCountMapEntry() {
    }

    public StatusCountMapEntry(String key, Integer count) {
        this.key = key;
        this.count = count;
    }
}
