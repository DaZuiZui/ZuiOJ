package com.dazuizui.business.domain.vo;

import com.dazuizui.basicapi.entry.AcContestQuestion;

import java.io.Serializable;
import java.util.List;

/**
 * 通过元素柴选查询
 */
public class QueryLogByElementVo implements Serializable {
    private Long count ;
    private List<AcContestQuestion> acContestQuestions;

    @Override
    public String toString() {
        return "QueryLogByElementVo{" +
                "count=" + count +
                ", acContestQuestions=" + acContestQuestions +
                '}';
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<AcContestQuestion> getAcContestQuestions() {
        return acContestQuestions;
    }

    public void setAcContestQuestions(List<AcContestQuestion> acContestQuestions) {
        this.acContestQuestions = acContestQuestions;
    }

    public QueryLogByElementVo() {
    }

    public QueryLogByElementVo(Long count, List<AcContestQuestion> acContestQuestions) {
        this.count = count;
        this.acContestQuestions = acContestQuestions;
    }
}
