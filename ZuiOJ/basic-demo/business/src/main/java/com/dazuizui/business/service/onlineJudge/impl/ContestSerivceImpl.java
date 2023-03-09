package com.dazuizui.business.service.onlineJudge.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.*;
import com.dazuizui.basicapi.entry.vo.ContestInfoVo;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.bo.AdminQueryGameInformationByPageBo;
import com.dazuizui.business.domain.vo.AdminQueryGameInformationByPageVo;
import com.dazuizui.business.mapper.CompetitionInfoMapper;
import com.dazuizui.business.mapper.ContestMapper;
import com.dazuizui.business.service.onlineJudge.ContestSerivce;
import com.dazuizui.business.util.RedisUtil;
import com.dazuizui.business.util.ThreadLocalUtil;
import com.dazuizui.business.util.TransactionUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private TransactionUtils transactionUtils;

    /**
     * 移除比赛页面
     * @Param id 比赛id
     */
    @Override
    public String removeTheContestById(Long id){
        TransactionStatus transactionStatus = transactionUtils.begin(TransactionDefinition.ISOLATION_READ_COMMITTED);
        try {
            //删除比赛简介信息
            Long aLong = conTestMapper.removeTheContestById(id);
            if (aLong == 0){
                transactionUtils.rollback(transactionStatus);
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            }
            //删除比赛详细信息
            aLong = conTestMapper.removeTheContestDetailedById(id);
            if (aLong == 0){
                transactionUtils.rollback(transactionStatus);
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            }
            //todo 删除参赛选手信息
            List<String> competitionInfos = competitionInfoMapper.selectAllUserInTheContestByContestId(id);
            /**
             * 删除redis中的缓存数据
             */
            redisUtil.batchDeletion(competitionInfos);
            //删除数据库
            competitionInfoMapper.deleteCompetitionInfoByContestId(id);

            //删除redis 比赛详细页面
            redisUtil.deleteKey(RedisKey.ZuiOJContestInfo+id);
        } catch (Exception e) {
            e.printStackTrace();
            transactionUtils.rollback(transactionStatus);
        }

        //提交事物
        transactionUtils.commit(transactionStatus);
        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,null, StatusCode.OK));
    }

    /**
     * 修改比赛信息
     * @param contest
     * @return
     */
    @Override
    public String updateContest(Contest contest){
        TransactionStatus transactionStatus = transactionUtils.begin(TransactionDefinition.ISOLATION_READ_COMMITTED);

        Long aLong = 0l;

        try {
            //修改竞赛简介信息
            aLong = conTestMapper.updateContest(contest);
            //添加失败
            if (aLong == 0){
               transactionUtils.rollback(transactionStatus);
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            }
            //修改竞赛详细Md文档信息
            aLong = conTestMapper.updateContestDetailed(contest);
            //添加失败
            if (aLong == 0){
                transactionUtils.rollback(transactionStatus);
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            }
            //修改redis
            redisUtil.setStringInRedis(RedisKey.ZuiOJContestInfo+contest.getId(),RedisKey.OutTime,contest);

            //提交事物
            transactionUtils.commit(transactionStatus);
        } catch (Exception e) {
            transactionUtils.rollback(transactionStatus);
            e.printStackTrace();
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
        }



        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,null, StatusCode.OK));
    }

    /**
     * 管理员分页查询数据
     * @param adminQueryGameInformationByPageBo
     * @return
     */
    @Override
    public String adminQueryGameInformationByPage( AdminQueryGameInformationByPageBo adminQueryGameInformationByPageBo){

        //获取比赛数据
        List<Contest> contests = conTestMapper.adminQueryGameInformationByPage(adminQueryGameInformationByPageBo);
        //查询多少个比赛
        Long count = conTestMapper.queryCoubtOfContest();
        AdminQueryGameInformationByPageVo adminQueryGameInformationByPageVo = new AdminQueryGameInformationByPageVo();
        adminQueryGameInformationByPageVo.setList(contests);
        adminQueryGameInformationByPageVo.setCount(count);
        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,adminQueryGameInformationByPageVo, StatusCode.OK));
    }

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
        conTest.setCreateTime(new Date());
        long l = conTestMapper.insertConTest(conTest);
        if (l == 0){
            //todo error
        }
        l = conTestMapper.insertConTestDetailed(conTest);
        if (l == 0){
            //todo error
        }
        //todo 添加到redis

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
        String idInJWTString = (String) map.get("id");
        Long idInJWt = Long.valueOf(idInJWTString);
        CompetitionInfo competitionInfo = new CompetitionInfo();
        competitionInfo.setUserId(idInJWt);
        competitionInfo.setContestId(id);

        //获取比赛介绍 todo redis缓存层优化
        Contest contest = conTestMapper.getEventById(id);
        contestInfoVo.setContest(contest);

        //查看是否已经报名

        CompetitionInfo competitionInfoInDB = (CompetitionInfo) redisUtil.getStringInRedis(RedisKey.ZuiOJConetstCompetitionInfo + competitionInfo.getContestId() + ":" + id);
        if (competitionInfoInDB == null){
            competitionInfoInDB = competitionInfoMapper.checkForEntry(competitionInfo);
            if (competitionInfoInDB != null){
                redisUtil.setStringInRedis(RedisKey.ZuiOJConetstCompetitionInfo + competitionInfo.getContestId() + ":" + id,RedisKey.OutTime,competitionInfoInDB);
            }
        }

        if (competitionInfoInDB != null){
            contestInfoVo.setCheckForEntry(true);
            //todo 检测是否被封禁

            //todo 是否满足获取题库
            System.err.println("????");

            return JSONArray.toJSONString(new ResponseVo<>("获取赛制通过id",contestInfoVo,"666"));
        }
        //todo 是否满足获取题库

        //未报名
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
