package com.dazuizui.business.domain.vo;

import com.dazuizui.basicapi.entry.AcContestQuestion;

import java.io.Serializable;
import java.util.List;

/**
 * 管理员查询提交记录VO
 */
public class QueryContestSubmissionLogVo implements Serializable {
    private Long id;
    private List<AcContestQuestion> acContestQuestions;

    @Override
    public String toString() {
        return "QueryContestSubmissionLogVo{" +
                "id=" + id +
                ", acContestQuestions=" + acContestQuestions +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<AcContestQuestion> getAcContestQuestions() {
        return acContestQuestions;
    }

    public void setAcContestQuestions(List<AcContestQuestion> acContestQuestions) {
        this.acContestQuestions = acContestQuestions;
    }

    public QueryContestSubmissionLogVo() {
    }

    public QueryContestSubmissionLogVo(Long id, List<AcContestQuestion> acContestQuestions) {
        this.id = id;
        this.acContestQuestions = acContestQuestions;
    }
}
