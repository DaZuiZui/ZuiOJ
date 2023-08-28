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


    @Override
    public String toString() {
        return "GetCheckDcInfoByRankingVo{" +
                "userMaxCoverageRateRes=" + userMaxCoverageRateRes +
                ", rankings=" + rankings +
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

    public GetCheckDcInfoByRankingVo() {
    }

    public GetCheckDcInfoByRankingVo(List<List<UserMaxCoverageRate>> userMaxCoverageRateRes, List<Ranking> rankings) {
        this.userMaxCoverageRateRes = userMaxCoverageRateRes;
        this.rankings = rankings;
    }
}
