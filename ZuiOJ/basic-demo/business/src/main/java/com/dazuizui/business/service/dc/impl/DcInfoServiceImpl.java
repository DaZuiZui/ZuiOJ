package com.dazuizui.business.service.dc.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.dazuizui.basicapi.entry.Ranking;
import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.StatusCodeMessage;
import com.dazuizui.basicapi.entry.vo.ContestQuestionVo;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.CodeInContest;
import com.dazuizui.business.domain.Coverage;
import com.dazuizui.business.domain.UserMaxCoverageRate;
import com.dazuizui.business.domain.bo.DCCoreRunBo;
import com.dazuizui.business.domain.bo.DcInfoBo;
import com.dazuizui.business.domain.bo.GetCheckDcInfoByRankingBo;
import com.dazuizui.business.domain.vo.GetCheckDcInfoByRankingVo;
import com.dazuizui.business.mapper.AcContestQuestionMapper;
import com.dazuizui.business.mapper.CompetitionQuestionBankMapper;
import com.dazuizui.business.mapper.CoverageMapper;
import com.dazuizui.business.mongodao.SubmmitionCodeInContestRepository;
import com.dazuizui.business.service.dc.DcInfoSerivce;
import com.dazuizui.business.service.onlineJudge.CompetitionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DcInfoServiceImpl implements DcInfoSerivce {
    @Autowired
    private AcContestQuestionMapper acContestQuestionMapper;
    @Autowired
    private SubmmitionCodeInContestRepository submmitionCodeInContestRepository;
    @Autowired
    private CoreServicxeImpl coreImpl;
    @Autowired
    private CompetitionInfoService competitionInfoService;
    @Autowired
    private CoverageMapper coverageMapper;
    @Autowired
    private CompetitionQuestionBankMapper competitionQuestionBankMapper;


    @Override
    public String dcInfoByContestIdAndQuestionId(DcInfoBo dcInfoBo) {
        //查看是否满足查重，如果不满足则删除之前的查重数据
        Coverage questionid = coverageMapper.findByContestIdAndQuestionid(dcInfoBo.getContestId(), dcInfoBo.getQuestionId());
        if (questionid == null){
            coverageMapper.deleteByContestIdAndQuestionid(dcInfoBo.getContestId(), dcInfoBo.getQuestionId());
        }

        //获取需要查重的代码
        Long questionId = dcInfoBo.getQuestionId();
        Page<CodeInContest> byQuestionIdAndStatus = submmitionCodeInContestRepository.findByContestIdAndQuestionIdAndStatus(dcInfoBo.getContestId(), questionId, 0, Pageable.unpaged());
        List<CodeInContest> content = byQuestionIdAndStatus.getContent();

        //进行查重
        List<DCCoreRunBo> res = coreImpl.run(content);

        //错误数据
        Long errorNumber = 0l;

        //查重数据保存到数据库
        for (int i = 0; i < res.size(); i++) {
            DCCoreRunBo dcCoreRunBo = res.get(i);
            Coverage coverage = new Coverage();
            coverage.setCoverageRate(dcCoreRunBo.getCoverage());
            coverage.setContestId(dcInfoBo.getContestId());
            coverage.setCreateBy(null);
            coverage.setCreateTime(new Date());
            coverage.setGuestId(dcCoreRunBo.getGuestId());
            coverage.setGuestCodeId(dcCoreRunBo.getGuestCodeId());
            coverage.setMasterId(dcCoreRunBo.getMasterId());
            coverage.setMasterCodeId(dcCoreRunBo.getMasterCodeId());
            coverage.setQuestionId(dcInfoBo.getQuestionId());
            Long aLong = coverageMapper.insertCoverageData(coverage);
            if (aLong == 0){
                errorNumber++;
            }
        }

        System.err.println("错误次数共"+errorNumber);

        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK ,errorNumber , StatusCode.OK));
    }

    @Override
    public String getCheckDcInfoByRanking(GetCheckDcInfoByRankingBo getCheckDcInfoByRankingBo) {
        System.err.println(getCheckDcInfoByRankingBo);
        //获取比赛排名
        String json = competitionInfoService.viewRanking(getCheckDcInfoByRankingBo.getContestId(), getCheckDcInfoByRankingBo.getStart(), getCheckDcInfoByRankingBo.getSize());
        System.out.println(json);

        //没有设计好，导致的繁琐
        getCheckDcInfoByRankingBo.setStart(getCheckDcInfoByRankingBo.getStart() * 25);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        ResponseVo responseVo = JSON.parseObject(json,ResponseVo.class);

        JSONObject jsonObject = JSON.parseObject(json);
        JSONArray rankinglistArray = jsonObject.getJSONObject("data").getJSONArray("rankinglist");
        Integer total = jsonObject.getJSONObject("data").getInteger("total");


        List<Ranking> rankings = rankinglistArray.toJavaList(Ranking.class);


        List<List<UserMaxCoverageRate>> userMaxCoverageRateRes = new ArrayList<>();

        //获取每个用户的最大重复度
        for (int i = 0; i < rankings.size(); i++) {

            List<UserMaxCoverageRate> userMaxCoverageRateByContestIdAndMasterId = coverageMapper.getUserMaxCoverageRateByContestIdAndMasterId(
                    getCheckDcInfoByRankingBo.getContestId(), rankings.get(i).getUserId()
            );

            userMaxCoverageRateRes.add(userMaxCoverageRateByContestIdAndMasterId);
        }

        //封装返回数据
        GetCheckDcInfoByRankingVo getCheckDcInfoByRankingVo = new GetCheckDcInfoByRankingVo();
        getCheckDcInfoByRankingVo.setRankings(rankings);
        getCheckDcInfoByRankingVo.setUserMaxCoverageRateRes(userMaxCoverageRateRes);
        getCheckDcInfoByRankingVo.setTotal(total);

        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK ,getCheckDcInfoByRankingVo , StatusCode.OK));
    }

    /**
     * 开始查重
     * @param contestId
     * @return
     */
    @Override
    public String startDC(Long contestId) {
        //通过当前比赛id获取比赛题目
        List<ContestQuestionVo> questionListByContestId = competitionQuestionBankMapper.getQuestionListByContestId(contestId);

        for (ContestQuestionVo contestQuestionVo : questionListByContestId) {
            DcInfoBo dcInfoBo = new DcInfoBo();
            dcInfoBo.setContestId(contestId);
            dcInfoBo.setQuestionId(contestQuestionVo.getId());
            this.dcInfoByContestIdAndQuestionId(dcInfoBo);
        }

        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK ,null , StatusCode.OK));
    }
}
