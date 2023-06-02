package com.dazuizui.business.domain.bo;

import java.io.Serializable;
import java.util.List;

/**
 * 批量恢复数据
 */
public class BatchRecoveryQuestionsBo implements Serializable {
    private String token;
    private List<Long> list;

    @Override
    public String toString() {
        return "BatchRecoveryQuestionsBo{" +
                "token='" + token + '\'' +
                ", list=" + list +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Long> getList() {
        return list;
    }

    public void setList(List<Long> list) {
        this.list = list;
    }

    public BatchRecoveryQuestionsBo() {
    }

    public BatchRecoveryQuestionsBo(String token, List<Long> list) {
        this.token = token;
        this.list = list;
    }
}
