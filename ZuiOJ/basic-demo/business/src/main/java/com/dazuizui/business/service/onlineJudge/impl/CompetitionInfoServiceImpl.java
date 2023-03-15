package com.dazuizui.business.service.onlineJudge.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.*;
import com.dazuizui.basicapi.entry.vo.RankingVo;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.CompetitionInfoInContest;
import com.dazuizui.business.domain.bo.AdminAddCompetitionInfoBo;
import com.dazuizui.business.domain.bo.DeleteAllCompetitionInfoByContestIdBo;
import com.dazuizui.business.domain.bo.PaglingQueryContestantsInThisContestBo;
import com.dazuizui.business.domain.vo.PaglingQueryContestantsInThisContestVo;
import com.dazuizui.business.mapper.CompetitionInfoMapper;
import com.dazuizui.business.mapper.UserMapper;
import com.dazuizui.business.service.onlineJudge.CompetitionInfoService;
import com.dazuizui.business.util.RedisUtil;
import com.dazuizui.business.util.TransactionUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import java.util.List;

/**
 * 参赛选手数据
 */
@Service
public class CompetitionInfoServiceImpl implements CompetitionInfoService {
    @Autowired
    private CompetitionInfoMapper competitionInfoMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private TransactionUtils transactionUtils;

    /**
     * 删除所有比赛选手通过比赛Id
     * @Param deleteAllCompetitionInfoByContestIdBo
     */
    @Override
    public String deleteAllCompetitionInfoByContestId(DeleteAllCompetitionInfoByContestIdBo deleteAllCompetitionInfoByContestIdBo){
        //查询数据的报名人数信息
        List<String> list = competitionInfoMapper.queryCompettionInfoRedisKeyByContestId(deleteAllCompetitionInfoByContestIdBo.getContestId());
        //事物开启
        TransactionStatus transactionStatus = transactionUtils.begin(TransactionDefinition.ISOLATION_READ_COMMITTED);

        try {
            //删除Rediskey
            redisUtil.batchDeletion(list);

            //删除数据库内容
            Long aLong = competitionInfoMapper.deleteAllCompetitionInfoByContestId(deleteAllCompetitionInfoByContestIdBo.getContestId());
            if (aLong == 0){
                //回滚
                transactionUtils.rollback(transactionStatus);
                return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
            }
        } catch (Exception e) {
            //回滚
            transactionUtils.rollback(transactionStatus);
            e.printStackTrace();
        }
        //事物提交
        transactionUtils.commit(transactionStatus);

        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,null, StatusCode.OK));
    }

    /**
     * 管理员添加比赛选手信息
     * @param adminAddCompetitionInfoBo
     * @return
     */
    @Override
    public String adminAddCompetitionInfo(AdminAddCompetitionInfoBo adminAddCompetitionInfoBo) {
        User user = userMapper.queryUserByUsername(adminAddCompetitionInfoBo.getUsername());
        if (user == null){
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.UserIsNull,"用户不存在", StatusCode.OK));
        }
        System.err.println("???");
        //获取比赛选手信息
        CompetitionInfo competitionInfo = this.checkForEntryByContestIdAnd(adminAddCompetitionInfoBo.getContestId(),user.getId());
        if (competitionInfo != null){
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.AlreadySignedUpGorTheCompetition,"选手已经报名比赛", StatusCode.OK));
        }

        //添加比赛选手信息到数据库
        Long aLong = competitionInfoMapper.adminAddCompetitionInfo(adminAddCompetitionInfoBo.getContestId(),user.getId());

        //添加成功
        if (aLong == 0){
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,"添加失败", StatusCode.Error));
        }

        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,"添加成功", StatusCode.OK));
    }

    /**
     * 查询比赛选手是否参赛
     * @param ContestId
     * @param userId
     * @return
     */
    @Override
    public CompetitionInfo checkForEntryByContestIdAnd(Long ContestId,Long userId){
        CompetitionInfo competitionInfoInDB = (CompetitionInfo) redisUtil.getStringInRedis(RedisKey.ZuiOJConetstCompetitionInfo + ContestId + ":" + userId);

        if (competitionInfoInDB == null){
            competitionInfoInDB = competitionInfoMapper.checkForEntryByContestIdAnd(ContestId, userId);
            if (competitionInfoInDB != null){
                redisUtil.setStringInRedis(RedisKey.ZuiOJConetstCompetitionInfo + ContestId + ":" + userId,RedisKey.OutTime,competitionInfoInDB);
            }
        }

        return competitionInfoInDB;
    }

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
