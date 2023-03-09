package com.dazuizui.business.domain.vo;

import com.dazuizui.basicapi.entry.CompetitionInfo;
import com.dazuizui.business.domain.CompetitionInfoInContest;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询比赛选手Bo
 */
public class PaglingQueryContestantsInThisContestVo implements Serializable {
    private Long count;
    private List<CompetitionInfoInContest> competitionInfos;

    @Override
    public String toString() {
        return "PaglingQueryContestantsInThisContestVo{" +
                "count=" + count +
                ", competitionInfos=" + competitionInfos +
                '}';
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<CompetitionInfoInContest> getCompetitionInfos() {
        return competitionInfos;
    }

    public void setCompetitionInfos(List<CompetitionInfoInContest> competitionInfos) {
        this.competitionInfos = competitionInfos;
    }

    public PaglingQueryContestantsInThisContestVo() {
    }

    public PaglingQueryContestantsInThisContestVo(Long count, List<CompetitionInfoInContest> competitionInfos) {
        this.count = count;
        this.competitionInfos = competitionInfos;
    }
}
