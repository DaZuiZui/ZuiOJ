package com.dazuizui.business.domain.vo;

import java.io.Serializable;

public class FindAcCountEveryQuestionByContestIdAndStatusVo implements Serializable {
    private String name;
    private Integer value;

    @Override
    public String toString() {
        return "FindAcCountEveryQuestionByContestIdAndStatusVo{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public FindAcCountEveryQuestionByContestIdAndStatusVo() {
    }

    public FindAcCountEveryQuestionByContestIdAndStatusVo(String name, Integer value) {
        this.name = name;
        this.value = value;
    }
}
