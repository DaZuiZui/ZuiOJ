package com.dazuizui.business.service.onlineJudge.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.CompetitionInfo;
import com.dazuizui.basicapi.entry.Contest;
import com.dazuizui.basicapi.entry.vo.ContestInfoVo;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.mapper.CompetitionInfoMapper;
import com.dazuizui.business.mapper.ContestMapper;
import com.dazuizui.business.service.onlineJudge.ContestSerivce;
import com.dazuizui.business.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yida yang
 * 竞赛模块接口实现类
 */
@Service
public class ContestSerivceImpl implements ContestSerivce {

    @Autowired
    private ContestMapper conTestMapper;
    @Autowired
    private CompetitionInfoMapper competitionInfoMapper;


    /**
     * 创建比赛
     * @param conTest
     * @return
     */
    @Override
    @Transactional
    public String postContest(Contest conTest) {
        //设置创建人
        String strId = (String) ThreadLocalUtil.mapThreadLocalOfJWT.get().get("userinfo").get("id");
        Long id = Long.valueOf(strId);
        conTest.setCreateById(id);

        long l = conTestMapper.insertConTest(conTest);
        if (l == 0){
            //todo error
        }
        l = conTestMapper.insertConTestDetailed(conTest);
        if (l == 0){
            //todo error
        }

        return JSONArray.toJSONString(new ResponseVo<>("创建比赛成功",null,"0x1001"));
    }

    /**
     * 获取未来赛制
     * @return
     */
    @Override
    public String getFutureEvents() {
        //获取服务器时间
        List<Contest> futureEvents = conTestMapper.getFutureEvents();
        return JSONArray.toJSONString(new ResponseVo<>("获取未来赛制",futureEvents,"0x1003"));
    }


    /**
     * 获取全部赛制
     * @return
     */
    @Override
    public String getAllEvents() {
        //获取未来赛制
        List<Contest> futureEvents = conTestMapper.getFutureEvents();
        //获取往期赛制
        List<Contest> pastevents = conTestMapper.getPastevents();
        Map<String,List<Contest>> map = new HashMap<>();
        map.put("future",futureEvents);
        map.put("past",pastevents);

        return JSONArray.toJSONString(new ResponseVo<>("获取全部赛制",map,"200"));
    }

    /**
     * 比赛选手举报
     * @param reportMessageText
     * @return
     */
    @Override
    public String competitorReport(String reportMessageText) {
        return null;
    }

    /**
     * 通过id获取赛制
     * @param id 赛制id
     * @return
     */
    @Override
    public String getEventById(Long id) {
        //信息初始化
        ContestInfoVo contestInfoVo = new ContestInfoVo();  //返回数据

        Map<String, Object> map = ThreadLocalUtil.mapThreadLocalOfJWT.get().get("userinfo");
        System.err.println(map);
        String idInJWTString = (String) map.get("id");
        Long idInJWt = Long.valueOf(idInJWTString);
        CompetitionInfo competitionInfo = new CompetitionInfo();
        competitionInfo.setUserId(idInJWt);
        competitionInfo.setContestId(id);

        //获取比赛介绍
        Contest contest = conTestMapper.getEventById(id);
        contestInfoVo.setContest(contest);

        //查看是否已经报名
        CompetitionInfo competitionInfoInDB = competitionInfoMapper.checkForEntry(competitionInfo);
        if (competitionInfoInDB != null){
            contestInfoVo.setCheckForEntry(true);
            //todo 检测是否被封禁

            //todo 是否满足获取题库
            System.err.println("????");

            return JSONArray.toJSONString(new ResponseVo<>("获取赛制通过id",contestInfoVo,"666"));
        }
        //todo 是否满足获取题库

        return JSONArray.toJSONString(new ResponseVo<>("获取赛制通过id",contestInfoVo,"200"));
    }

    /**
     * 报名比赛
     * @param contestId
     * @return
     */
    @Override
    public String signUpForTheCompetition(Long contestId) {

        Map<String, Object> map = ThreadLocalUtil.mapThreadLocalOfJWT.get().get("userinfo");
        String idInJWTString = (String) map.get("id");
        Long idInJWt = Long.valueOf(idInJWTString);
        CompetitionInfo competitionInfo = new CompetitionInfo();
        competitionInfo.setContestId(contestId);
        competitionInfo.setUserId(idInJWt);
        //todo 查看此人是否为封禁状态

        System.err.println(competitionInfo);
        //查看此人是否有报名此比赛
        CompetitionInfo competitionInfoInDB = competitionInfoMapper.checkForEntry(competitionInfo);
        if (competitionInfoInDB != null){
            return JSONArray.toJSONString(new ResponseVo<>("报名失败，您已经参加此比赛",null,"666"));
        }

        //报名比赛
        String username = (String) map.get("username");
        competitionInfo.setCreateById(idInJWt);
        competitionInfo.setCreateByName(username);
        competitionInfo.setCreateTime(new Date());
        competitionInfoMapper.addCompetitionInfo(competitionInfo);


        return JSONArray.toJSONString(new ResponseVo<>("报名成功，请注意您的邮箱。",null,"200"));
    }
}
