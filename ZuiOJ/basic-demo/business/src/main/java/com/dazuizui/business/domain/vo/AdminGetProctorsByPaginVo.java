package com.dazuizui.business.domain.vo;

import com.dazuizui.business.domain.Proctor;
import com.dazuizui.business.domain.ProctorInfo;

import java.io.Serializable;
import java.util.List;

/**
 * 管理员获取监考人员数据
 */
public class AdminGetProctorsByPaginVo implements Serializable {
    private Long count;
    private List<ProctorInfo> proctors;

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

    public List<ProctorInfo> getProctors() {
        return proctors;
    }

    public void setProctors(List<ProctorInfo> proctors) {
        this.proctors = proctors;
    }

    public AdminGetProctorsByPaginVo() {
    }

    public AdminGetProctorsByPaginVo(Long count, List<ProctorInfo> proctors) {
        this.count = count;
        this.proctors = proctors;
    }
}
