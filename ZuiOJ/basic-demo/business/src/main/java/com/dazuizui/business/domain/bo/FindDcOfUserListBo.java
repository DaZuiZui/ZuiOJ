package com.dazuizui.business.domain.bo;

import com.dazuizui.business.domain.Coverage;
import com.dazuizui.business.domain.FindDcOfUser;

import java.io.Serializable;
import java.util.List;

public class FindDcOfUserListBo implements Serializable {
    private Long count;
    private List<FindDcOfUser> list;

    @Override
    public String toString() {
        return "FindDcOfUserListBo{" +
                "count=" + count +
                ", list=" + list +
                '}';
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<FindDcOfUser> getList() {
        return list;
    }

    public void setList(List<FindDcOfUser> list) {
        this.list = list;
    }

    public FindDcOfUserListBo() {
    }

    public FindDcOfUserListBo(Long count, List<FindDcOfUser> list) {
        this.count = count;
        this.list = list;
    }
}
