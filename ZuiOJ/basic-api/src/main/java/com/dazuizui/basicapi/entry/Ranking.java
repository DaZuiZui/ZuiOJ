package com.dazuizui.basicapi.entry;

import java.io.Serializable;

/**
 * 榜单数据
 */
public class Ranking implements Serializable {
    private Long second;
    private Long userId;
    private String createByName;
    private Integer count;

    @Override
    public String toString() {
        return "Ranking{" +
                "second=" + second +
                ", userId=" + userId +
                ", createByName='" + createByName + '\'' +
                ", count=" + count +
                '}';
    }

    public Long getSecond() {
        return second;
    }

    public void setSecond(Long second) {
        this.second = second;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Ranking() {
    }

    public Ranking(Long second, Long userId, String createByName, Integer count) {
        this.second = second;
        this.userId = userId;
        this.createByName = createByName;
        this.count = count;
    }
}
