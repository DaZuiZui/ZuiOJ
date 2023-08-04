package com.dazuizui.business.service.onlineJudge.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.*;
import com.dazuizui.basicapi.entry.vo.ContestInfoVo;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.RedisKey;
import com.dazuizui.business.domain.vo.AdminGetArticleByPaginVo;
import com.dazuizui.business.mapper.CompetitionInfoMapper;
import com.dazuizui.business.mapper.ContestMapper;
import com.dazuizui.business.mapper.ProctorAttributeMapper;
import com.dazuizui.business.service.onlineJudge.ContestSerivce;
import com.dazuizui.business.util.RedisUtil;
import com.dazuizui.business.util.ThreadLocalUtil;
import com.dazuizui.business.util.TransactionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
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
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private TransactionUtils transactionUtils;
    @Autowired
    private ProctorAttributeMapper proctorAttributeMapper;

    /**
     * @author Bryan Yang(杨易达)
     * 移除比赛信息
     * remove the competition
     * @param id 比赛id competition ID
     *
     *    在进行删除了比赛的简介信息和比赛的详细信息和该考场的监考人员和redis中缓存的比赛内容信息数据和所有参赛选手的记录。
     *
     *    on the business layer,the profile info and detailed info of the competition,the invigilators of the
     *    examination room ,the contest info date of competition cached in redis and records of contestands are deleted
     *
     * @Param id 比赛id Competition ID
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
            //todo 删除监考数据

            //删除参赛选手信息
            List<String> competitionInfos = competitionInfoMapper.selectAllUserInTheContestByContestId(id);

            //删除redis中的缓存数据

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
     * @author Bryan Yang(杨易达 Dazui)
     * 修改比赛信息
     *    修改了比赛的简介信息和详细信息和更新redis事务
     *
     * Modify contest info
     *    modifyed the profile info and details of the contet and updated the redis data
     *
     * @param contest 比赛数据实体
     * @return
     */
    @Override
    public String updateContest(Contest contest){
        TransactionStatus transactionStatus = transactionUtils.begin(TransactionDefinition.ISOLATION_READ_COMMITTED);

        //操作熟练
        Long numbersOfOpention = 0l;

        try {
            //修改竞赛简介信息
            numbersOfOpention = conTestMapper.updateContest(contest);
            //添加失败
            if (numbersOfOpention.longValue() == 0){
               transactionUtils.rollback(transactionStatus);
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            }
            //修改竞赛详细Md文档信息
            numbersOfOpention = conTestMapper.updateContestDetailed(contest);
            //添加失败
            if (numbersOfOpention.longValue() == 0){
                transactionUtils.rollback(transactionStatus);
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            }
            //修改redis
            redisUtil.setStringInRedis(RedisKey.ZuiOJContestInfo+contest.getId(),RedisKey.OutTime,contest);

            transactionUtils.commit(transactionStatus);
        } catch (Exception e) {
            transactionUtils.rollback(transactionStatus);
            e.printStackTrace();
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
        }



        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,null, StatusCode.OK));
    }

    /**
     * @authro Bryan Yang(Dazui)
     * 管理员分页查询比赛数据
     * Admin query game data by page
     *    根据AdminQueryGameInformationByPageVo查询条件进行分页查询
     * @param adminQueryGameInformationByPageBo 分页
     * @return String
     */
    @Override
    public String adminQueryGameInformationByPage( AdminGetArticleByPaginVo.AdminQueryGameInformationByPageVo adminQueryGameInformationByPageBo){
        //获取比赛数据
        List<Contest> contests = conTestMapper.adminQueryGameInformationByPage(adminQueryGameInformationByPageBo);
        //查询多少个比赛
        Long count = conTestMapper.queryCoubtOfContest();
        com.dazuizui.business.domain.vo.AdminQueryGameInformationByPageVo adminQueryGameInformationByPageVo = new com.dazuizui.business.domain.vo.AdminQueryGameInformationByPageVo();
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
        //添加比赛简略信息
        long l = conTestMapper.insertConTest(conTest);
        if (l == 0){
            //todo error
        }
        //添加contest详细页面
        l = conTestMapper.insertConTestDetailed(conTest);
        if (l == 0){
            //todo error
        }
        System.err.println(conTest.getId());
        //添加监考信息
        l = proctorAttributeMapper.createAttributeOfProctors(conTest.getId());
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
        String idInJWTString = (String) map.get("id");
        Long idInJWt = Long.valueOf(idInJWTString);
        CompetitionInfo competitionInfo = new CompetitionInfo();
        competitionInfo.setUserId(idInJWt);
        competitionInfo.setContestId(id);

        //获取比赛介绍 todo redis缓存层优化
        Contest contest = conTestMapper.getEventById(id);
        contestInfoVo.setContest(contest);

        //查看是否已经报名
        System.err.println(competitionInfo.getUserId()+"idInJWt="+idInJWt+"abd "+id);
        //CompetitionInfo competitionInfoInDB = competitionInfoMapper.checkForEntry(competitionInfo);

        CompetitionInfo competitionInfoInDB
                = (CompetitionInfo) redisUtil.getStringInRedis(RedisKey.ZuiOJConetstCompetitionInfo + competitionInfo.getContestId() + ":" + competitionInfo.getUserId());
        if (competitionInfoInDB == null){
             competitionInfoInDB = competitionInfoMapper.checkForEntry(competitionInfo);
            if (competitionInfoInDB != null){
                redisUtil.setStringInRedis(RedisKey.ZuiOJConetstCompetitionInfo + competitionInfo.getContestId() + ":" + competitionInfo.getUserId(),RedisKey.OutTime,competitionInfoInDB);
            }
        }

        //数据库
        System.out.println(competitionInfoInDB);
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
