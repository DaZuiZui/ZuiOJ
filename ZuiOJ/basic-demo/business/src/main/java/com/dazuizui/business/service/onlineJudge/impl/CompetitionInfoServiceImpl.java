package com.dazuizui.business.service.onlineJudge.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.*;
import com.dazuizui.basicapi.entry.vo.RankingVo;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.CompetitionInfoInContest;
import com.dazuizui.business.domain.bo.PaglingQueryContestantsInThisContestBo;
import com.dazuizui.business.domain.vo.PaglingQueryContestantsInThisContestVo;
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
     * 分页查询参赛选手信息在这个比赛
     * @param paglingQueryContestantsInThisContestBo
     * @return
     */
    @Override
    public String paglingQueryContestantsInThisContest(PaglingQueryContestantsInThisContestBo paglingQueryContestantsInThisContestBo) {
        //分页获取参赛选手数据
        List<CompetitionInfoInContest> competitionInfos = competitionInfoMapper.paglingQueryContestantsInThisContest(paglingQueryContestantsInThisContestBo);
        //获取参选选手个数在当前这个竞赛
        Long count = competitionInfoMapper.queryConQueryTheNumberOfContestantstest(paglingQueryContestantsInThisContestBo.getContestId());
        //封装
        PaglingQueryContestantsInThisContestVo paglingQueryContestantsInThisContestVo = new PaglingQueryContestantsInThisContestVo();
        paglingQueryContestantsInThisContestVo.setCount(count);
        paglingQueryContestantsInThisContestVo.setCompetitionInfos(competitionInfos);
        System.out.println(paglingQueryContestantsInThisContestVo);
        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,paglingQueryContestantsInThisContestVo, StatusCode.OK));
    }

    /**
     * 获取榜单
     * @param contestId
     * @param page
     * @param numbers
     * @return
     */
    @Override
    public String viewRanking(Long contestId,Integer page,Integer numbers) {

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
