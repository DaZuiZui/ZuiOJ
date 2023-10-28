package com.dazuizui.business.domain.bo;

import java.io.Serializable;

/**
 * @author Bryan yang y51288033@outlook.com
 * 检查用户比赛时提交代码详细信息Bo
 */
public class CheckTheSubmitQuesitonDetailInfoBo implements Serializable {
    private Long userId;
    private Long contestId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getContestId() {
        return contestId;
    }

    public void setContestId(Long contestId) {
        this.contestId = contestId;
    }

    @Override
    public String toString() {
        return "CheckTheSubmitQuesitonDetailInfoBo{" +
                "userId=" + userId +
                ", contestId=" + contestId +
                '}';
    }

    public CheckTheSubmitQuesitonDetailInfoBo() {
    }

    public CheckTheSubmitQuesitonDetailInfoBo(Long userId, Long contestId) {
        this.userId = userId;
        this.contestId = contestId;
    }
}
