package com.dazuizui.business.domain.vo;

import com.dazuizui.basicapi.entry.Contest;

import java.io.Serializable;
import java.util.List;

public class ProctorGetFutureEventsInfoVo implements Serializable {
    private List<Contest> contests;
    private Long count;

    @Override
    public String toString() {
        return "ProctorGetFutureEventsInfoVo{" +
                "contests=" + contests +
                ", count=" + count +
                '}';
    }

    public List<Contest> getContests() {
        return contests;
    }

    public void setContests(List<Contest> contests) {
        this.contests = contests;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public ProctorGetFutureEventsInfoVo() {
    }

    public ProctorGetFutureEventsInfoVo(List<Contest> contests, Long count) {
        this.contests = contests;
        this.count = count;
    }
}
