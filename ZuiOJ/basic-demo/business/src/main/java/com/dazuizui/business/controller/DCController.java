package com.dazuizui.business.controller;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.bo.FindByContestIdAndQuestionIdAndMasterOdBo;
import com.dazuizui.business.domain.bo.GetCheckDcInfoByRankingBo;
import com.dazuizui.business.domain.bo.StartCheckingBo;
import com.dazuizui.business.service.dc.impl.DcInfoServiceImpl;
import com.dazuizui.business.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        ThreadLocalUtil.mapThreadLocal.remove();
        if ( map.get("error") != null) {
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return dcInfoService.startDC(startCheckingBo.getContestId());
    }


    /**
     * @author Bryan Yang(Dazui) 30/8/20223
     * todo aop
     * @param findByContestIdAndQuestionIdAndMasterOdBo
     * @return
     */
    @PostMapping("/admin/viewDetailedData")
    public String findByContestIdAndQuestionIdAndMasterId(@RequestBody FindByContestIdAndQuestionIdAndMasterOdBo findByContestIdAndQuestionIdAndMasterOdBo){
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        ThreadLocalUtil.mapThreadLocal.remove();
        if ( map.get("error") != null) {
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return dcInfoService.findByContestIdAndQuestionIdAndMasterId(findByContestIdAndQuestionIdAndMasterOdBo);
    }
}
