package com.dazuizui.business.domain.vo;

public class StatusCountDTO {

    private String status;
    private long count;

    // 构造函数、getter 和 setter 方法

    public StatusCountDTO(String status, long count) {
        this.status = status;
        this.count = count;
    }
}