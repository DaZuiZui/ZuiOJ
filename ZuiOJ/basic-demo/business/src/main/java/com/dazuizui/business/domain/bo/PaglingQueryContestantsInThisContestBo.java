package com.dazuizui.business.domain.bo;

import java.io.Serializable;

/**
 * 分页查询参赛选手在当前比赛Bo
 */
public class PaglingQueryContestantsInThisContestBo implements Serializable {
    private Long contestId;     //比赛id
    private Long page;          //页数
    private Long size;          //大小
    private String token;       //用户token

    @Override
    public String toString() {
        return "PaglingQueryContestantsInThisContestBo{" +
                "contestId=" + contestId +
                ", page=" + page +
                ", size=" + size +
                ", token='" + token + '\'' +
                '}';
    }

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public PaglingQueryContestantsInThisContestBo() {
    }

    public PaglingQueryContestantsInThisContestBo(Long contestId, Long page, Long size, String token) {
        this.contestId = contestId;
        this.page = page;
        this.size = size;
        this.token = token;
    }
}
