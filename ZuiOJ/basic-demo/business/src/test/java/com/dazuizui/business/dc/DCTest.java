package com.dazuizui.business.dc;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dazuizui.basicapi.entry.Ranking;
import com.dazuizui.business.domain.UserMaxCoverageRate;
import com.dazuizui.business.domain.bo.DcInfoBo;
import com.dazuizui.business.domain.bo.GetCheckDcInfoByRankingBo;
import com.dazuizui.business.service.dc.impl.DcInfoServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class DCTest {
    @Autowired
    private DcInfoServiceImpl dcInfo;

    @Test
    public void dcTest(){
        DcInfoBo dcInfoBo = new DcInfoBo();
        dcInfoBo.setQuestionId(10101l);
        dcInfoBo.setContestId(58l);
        dcInfo.startDC(58l);
    }
    @Test
    public void getCheckDcInfoByRanking(){
        GetCheckDcInfoByRankingBo getCheckDcInfoByRankingBo = new GetCheckDcInfoByRankingBo();
        getCheckDcInfoByRankingBo.setSize(10);
        getCheckDcInfoByRankingBo.setContestId(58l);
        getCheckDcInfoByRankingBo.setStart(0);
        String json = dcInfo.getCheckDcInfoByRanking(getCheckDcInfoByRankingBo);
//        GetCheckDcInfoByRankingVo getCheckDcInfoByRankingVo = JSON.parseObject(checkDcInfoByRanking, GetCheckDcInfoByRankingVo.class);

        JSONObject jsonObject = JSON.parseObject(json);
        JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("rankings");
        List<Ranking> rankings = jsonArray.toJavaList(Ranking.class);

        // 解析 userMaxCoverageRateRes 字段
        JSONArray userMaxCoverageRateArray = jsonObject.getJSONObject("data").getJSONArray("userMaxCoverageRateRes");
        List<List<UserMaxCoverageRate>> userMaxCoverageRateRes = new ArrayList<>();
        for (int i = 0; i < userMaxCoverageRateArray.size(); i++) {
            JSONArray innerArray = userMaxCoverageRateArray.getJSONArray(i);
            List<UserMaxCoverageRate> userMaxCoverageRate = innerArray.toJavaList(UserMaxCoverageRate.class);
            userMaxCoverageRateRes.add(userMaxCoverageRate);
        }

//
        for (int i = 0; i < rankings.size(); i++) {
            System.out.println("名字"+rankings.get(i).getCreateByName());
            for (int j = 0; j < userMaxCoverageRateRes.get(i).size(); j++) {
                System.out.print("\t "+userMaxCoverageRateRes.get(i).get(j).getQuestionName());
                System.out.print("重复率 "+userMaxCoverageRateRes.get(i).get(j).getCoverageRate());
            }
            System.out.println();

        }
    }
}
