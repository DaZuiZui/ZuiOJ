package com.dazuizui.business.domain.vo;

import com.dazuizui.business.domain.CodeInContest;

import java.io.Serializable;
import java.util.List;

/**
 * 返回筛选查询比赛保存代码信息
 */
public class FilterQueryMatchSaveCodeVo implements Serializable {
    private List<CodeInContest> codeInContestList;
    private Long count;

    @Override
    public String toString() {
        return "FilterQueryMatchSaveCodeVo{" +
                "codeInContestList=" + codeInContestList +
                ", count=" + count +
                '}';
    }

    public List<CodeInContest> getCodeInContestList() {
        return codeInContestList;
    }

    public void setCodeInContestList(List<CodeInContest> codeInContestList) {
        this.codeInContestList = codeInContestList;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public FilterQueryMatchSaveCodeVo() {
    }

    public FilterQueryMatchSaveCodeVo(List<CodeInContest> codeInContestList, Long count) {
        this.codeInContestList = codeInContestList;
        this.count = count;
    }
}
