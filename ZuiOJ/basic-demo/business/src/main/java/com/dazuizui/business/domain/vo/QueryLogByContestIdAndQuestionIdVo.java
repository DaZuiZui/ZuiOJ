package com.dazuizui.business.domain.vo;

import com.dazuizui.basicapi.entry.AcContestQuestion;

import java.io.Serializable;
import java.util.List;

/**
 * 通过contestId和questionId分页查询数据的结果数据。
 */
public class QueryLogByContestIdAndQuestionIdVo implements Serializable {
    private List<AcContestQuestion> acContestQuestions;
    private Long count;

    @Override
    public String toString() {
        return "QueryLogByContestIdAndQuestionIdVo{" +
                "acContestQuestions=" + acContestQuestions +
                ", count=" + count +
                '}';
    }

    public List<AcContestQuestion> getAcContestQuestions() {
        return acContestQuestions;
    }

    public void setAcContestQuestions(List<AcContestQuestion> acContestQuestions) {
        this.acContestQuestions = acContestQuestions;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public QueryLogByContestIdAndQuestionIdVo() {
    }

    public QueryLogByContestIdAndQuestionIdVo(List<AcContestQuestion> acContestQuestions, Long count) {
        this.acContestQuestions = acContestQuestions;
        this.count = count;
    }
}
