package com.dazuizui.business.controller;

import com.dazuizui.business.domain.bo.GetCheckDcInfoByRankingBo;
import com.dazuizui.business.domain.bo.StartCheckingBo;
import com.dazuizui.business.service.dc.impl.DcInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/DC")
public class DCController {
    @Autowired
    private DcInfoServiceImpl dcInfoService;

    /**
     * 通过比赛排名获取每题查重最高因子内容
     * @param getCheckDcInfoByRankingBo
     * @return
     */
    @PostMapping("/getCheckDcInfoByRanking")
    public String getCheckDcInfoByRanking(@RequestBody GetCheckDcInfoByRankingBo getCheckDcInfoByRankingBo){

        return dcInfoService.getCheckDcInfoByRanking(getCheckDcInfoByRankingBo);
    }

    /**
     * 开始查重
     * @param startCheckingBo
     * @return
     */
    @PostMapping("/admin/startChecking")
    public String startChecking(@RequestBody StartCheckingBo startCheckingBo){

        return dcInfoService.startDC(startCheckingBo.getContestId());
    }
}
