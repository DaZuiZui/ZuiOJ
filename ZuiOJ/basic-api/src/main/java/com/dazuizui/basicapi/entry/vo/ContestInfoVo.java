package com.dazuizui.basicapi.entry.vo;

import com.dazuizui.basicapi.entry.Contest;

import java.io.Serializable;

public class ContestInfoVo implements Serializable {

    //比赛信息
    private Contest contest;

    @Override
    public String toString() {
        return "ContestInfoVo{" +
                "contest=" + contest +
                '}';
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    public ContestInfoVo() {
    }

    public ContestInfoVo(Contest contest) {
        this.contest = contest;
    }


}
