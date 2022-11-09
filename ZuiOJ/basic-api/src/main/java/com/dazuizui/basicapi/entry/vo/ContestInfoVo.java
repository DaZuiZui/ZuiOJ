package com.dazuizui.basicapi.entry.vo;

import com.dazuizui.basicapi.entry.Contest;

import java.io.Serializable;

public class ContestInfoVo implements Serializable {

    //比赛信息
    private Contest contest;
    private boolean checkForEntry; //是否已经参加

    @Override
    public String toString() {
        return "ContestInfoVo{" +
                "contest=" + contest +
                ", checkForEntry=" + checkForEntry +
                '}';
    }

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    public boolean isCheckForEntry() {
        return checkForEntry;
    }

    public void setCheckForEntry(boolean checkForEntry) {
        this.checkForEntry = checkForEntry;
    }

    public ContestInfoVo() {
    }

    public ContestInfoVo(Contest contest, boolean checkForEntry) {
        this.contest = contest;
        this.checkForEntry = checkForEntry;
    }
}
