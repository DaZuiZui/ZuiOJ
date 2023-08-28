package com.dazuizui.business.controller;

import com.dazuizui.business.domain.bo.GetCheckDcInfoByRankingBo;
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

    @PostMapping("/getCheckDcInfoByRanking")
    public String getCheckDcInfoByRanking(@RequestBody GetCheckDcInfoByRankingBo getCheckDcInfoByRankingBo){
        return dcInfoService.getCheckDcInfoByRanking(getCheckDcInfoByRankingBo);
    }
}
