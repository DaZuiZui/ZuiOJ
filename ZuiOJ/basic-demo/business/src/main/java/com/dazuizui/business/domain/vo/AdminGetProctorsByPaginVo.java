package com.dazuizui.business.domain.vo;

import com.dazuizui.business.domain.Proctor;

import java.io.Serializable;
import java.util.List;

/**
 * 管理员获取监考人员数据
 */
public class AdminGetProctorsByPaginVo implements Serializable {
    private Long count;
    private List<Proctor> proctors;

    @Override
    public String toString() {
        return "AdminGetProctorsByPaginVo{" +
                "count=" + count +
                ", proctors=" + proctors +
                '}';
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<Proctor> getProctors() {
        return proctors;
    }

    public void setProctors(List<Proctor> proctors) {
        this.proctors = proctors;
    }

    public AdminGetProctorsByPaginVo() {
    }

    public AdminGetProctorsByPaginVo(Long count, List<Proctor> proctors) {
        this.count = count;
        this.proctors = proctors;
    }
}
