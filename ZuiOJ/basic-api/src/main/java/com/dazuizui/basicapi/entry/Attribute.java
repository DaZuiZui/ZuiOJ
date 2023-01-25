package com.dazuizui.basicapi.entry;

import java.io.Serializable;

/**
 * 表属性 用来记录表的数据
 */
public class Attribute implements Serializable {
    private Long id;
    private String formname;
    private Long formcount;

    @Override
    public String toString() {
        return "Attribute{" +
                "id=" + id +
                ", formname='" + formname + '\'' +
                ", formcount=" + formcount +
                '}';
    }

    public Attribute() {
    }

    public Attribute(Long id, String formname, Long formcount) {
        this.id = id;
        this.formname = formname;
        this.formcount = formcount;
    }
}
