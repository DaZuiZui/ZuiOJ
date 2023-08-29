package com.dazuizui.business.service.dc;


import com.dazuizui.business.domain.Coverage;
import com.dazuizui.business.domain.bo.DcInfoBo;
import com.dazuizui.business.domain.bo.FindByContestIdAndQuestionIdAndMasterOdBo;
import com.dazuizui.business.domain.bo.GetCheckDcInfoByRankingBo;

import java.util.List;

public interface DcInfoSerivce {
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

    /**
     * 通过比赛id和问题id和主人id进行查找对应的详细数据
     * @return
     */
    public String findByContestIdAndQuestionIdAndMasterId(FindByContestIdAndQuestionIdAndMasterOdBo findByContestIdAndQuestionIdAndMasterOdBo);
}
