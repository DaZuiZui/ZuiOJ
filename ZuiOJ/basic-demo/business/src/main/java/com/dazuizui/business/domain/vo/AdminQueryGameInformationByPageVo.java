package com.dazuizui.business.domain.vo;

import com.dazuizui.basicapi.entry.Contest;

import java.io.Serializable;
import java.util.List;

/**
 * 管理员查询比赛分页数据
 */
public class AdminQueryGameInformationByPageVo implements Serializable {
    private List<Contest> list;
    private Long count;

    @Override
    public String toString() {
        return "AdminQueryGameInformationByPageVo{" +
                "list=" + list +
                ", count=" + count +
                '}';
    }

    public List<Contest> getList() {
        return list;
    }

    public void setList(List<Contest> list) {
        this.list = list;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public AdminQueryGameInformationByPageVo() {
    }

    public AdminQueryGameInformationByPageVo(List<Contest> list, Long count) {
        this.list = list;
        this.count = count;
    }
}
