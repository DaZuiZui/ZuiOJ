package com.dazuizui.basicapi.entry.vo;

import com.dazuizui.basicapi.entry.Ranking;

import java.io.Serializable;
import java.util.List;

public class RankingVo implements Serializable {
    private List<Ranking> rankinglist;
    private Long total;

    @Override
    public String toString() {
        return "RankingVo{" +
                "rankinglist=" + rankinglist +
                ", total=" + total +
                '}';
    }

    public List<Ranking> getRankinglist() {
        return rankinglist;
    }

    public void setRankinglist(List<Ranking> rankinglist) {
        this.rankinglist = rankinglist;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public RankingVo() {
    }

    public RankingVo(List<Ranking> rankinglist, Long total) {
        this.rankinglist = rankinglist;
        this.total = total;
    }
}
