package com.dazuizui.business.service.onlineJudge.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.GetTotal;
import com.dazuizui.basicapi.entry.Ranking;
import com.dazuizui.basicapi.entry.vo.RankingVo;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.mapper.CompetitionInfoMapper;
import com.dazuizui.business.service.onlineJudge.CompetitionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 参赛选手数据
 */
@Service
public class CompetitionInfoServiceImpl implements CompetitionInfoService {
    @Autowired
    private CompetitionInfoMapper competitionInfoMapper;

    /**
     * 获取榜单
     * @param contestId
     * @param page
     * @param numbers
     * @return
     */
    @Override
    public String viewRanking(Long contestId,Integer page,Integer numbers) {
        System.err.println("?????");
        //有效选手个数
        RankingVo rankingVo = new RankingVo();
        GetTotal getTotalEntry = competitionInfoMapper.getTotal(contestId);
        Long total = getTotalEntry.getCount();

        //获取榜单
        List<Ranking> rankingVos = competitionInfoMapper.viewRanking(getTotalEntry.getStartTime(),contestId, page*25, numbers);

        rankingVo.setRankinglist(rankingVos);
        rankingVo.setTotal(total);

        return JSONArray.toJSONString(new ResponseVo<>("获取榜单成功获取页数page is"+contestId ,rankingVo,"200"));
    }

}
