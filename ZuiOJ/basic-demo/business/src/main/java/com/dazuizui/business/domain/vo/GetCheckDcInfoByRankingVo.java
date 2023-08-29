package com.dazuizui.business.domain.vo;

import com.dazuizui.basicapi.entry.Ranking;
import com.dazuizui.business.domain.UserMaxCoverageRate;

import java.io.Serializable;
import java.util.List;

/**
 * 用户排名包含每个题最大查重率
 */
public class GetCheckDcInfoByRankingVo implements Serializable {
    //用户的最大重复率
    private List<List<UserMaxCoverageRate>> userMaxCoverageRateRes;
    //用户排名
    private List<Ranking> rankings;
    //有效比赛选手个数
    private Integer total;

    @Override
    public String toString() {
        return "GetCheckDcInfoByRankingVo{" +
                "userMaxCoverageRateRes=" + userMaxCoverageRateRes +
                ", rankings=" + rankings +
                ", total=" + total +
                '}';
    }

    public List<List<UserMaxCoverageRate>> getUserMaxCoverageRateRes() {
        return userMaxCoverageRateRes;
    }

    public void setUserMaxCoverageRateRes(List<List<UserMaxCoverageRate>> userMaxCoverageRateRes) {
        this.userMaxCoverageRateRes = userMaxCoverageRateRes;
    }

    public List<Ranking> getRankings() {
        return rankings;
    }

    public void setRankings(List<Ranking> rankings) {
        this.rankings = rankings;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public GetCheckDcInfoByRankingVo() {
    }

    public GetCheckDcInfoByRankingVo(List<List<UserMaxCoverageRate>> userMaxCoverageRateRes, List<Ranking> rankings, Integer total) {
        this.userMaxCoverageRateRes = userMaxCoverageRateRes;
        this.rankings = rankings;
        this.total = total;
    }
}
