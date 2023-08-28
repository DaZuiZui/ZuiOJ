package com.dazuizui.business.service.dc;


import com.dazuizui.business.domain.bo.DcInfoBo;
import com.dazuizui.business.domain.bo.GetCheckDcInfoByRankingBo;

public interface DcInfo {
    /**
     * 进行查重
     * @param dcInfoBo
     * @return
     */
    public String dcInfoByContestIdAndQuestionId(DcInfoBo dcInfoBo);

    /**
     * 获取查重结果
     * @return
     */
    public String getCheckDcInfoByRanking(GetCheckDcInfoByRankingBo getCheckDcInfoByRankingBo);

    /**
     * 开始查重
     * @return
     */
    public String startDC(Long contestId);
}
