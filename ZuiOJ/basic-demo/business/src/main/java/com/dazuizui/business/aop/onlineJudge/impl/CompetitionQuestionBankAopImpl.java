package com.dazuizui.business.aop.onlineJudge.impl;

import com.dazuizui.basicapi.entry.*;
import com.dazuizui.business.aop.onlineJudge.CompetitionQuestionBankAop;
import com.dazuizui.business.mapper.CompetitionInfoMapper;
import com.dazuizui.business.service.user.UserService;
import com.dazuizui.business.util.JwtUtil;
import com.dazuizui.business.util.RedisUtil;
import com.dazuizui.business.util.ThreadLocalUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


/**
 *  比赛与题库关联的Aop
 */
@Component
@Aspect
public class CompetitionQuestionBankAopImpl implements CompetitionQuestionBankAop {

    @Autowired
    private CompetitionInfoMapper competitionInfoMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 添加比赛关联题目前置Aop
     *      主要负责身份验证和权限验证
     * @return
     */
    @Override
    public String addCompetitionQuestion(JoinPoint joinpoint) throws Exception {
        Object[] args = joinpoint.getArgs();
        String token = (String) args[0];
        if (token != null){
            Map<String, Object> map = null;
            try {
                map = JwtUtil.analysis(token);
                ThreadLocalUtil.mapThreadLocalOfJWT.get().put("userinfo",map);
                //获取登入者id
                String strId = (String) map.get("id");
                Long id = Long.valueOf(strId);
            } catch (Exception e) {
                ThreadLocalUtil.mapThreadLocal.get().put("error","身份验证过期");
                ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.authenticationExpired);
            }
        }
        return null;
    }

    /**
     * 通过contest id for get question list interface
     * @param joinpoint
     * @return
     * @throws Exception
     */
    @Override
    public String getQuestionListByContestId(JoinPoint joinpoint) throws Exception {
        Object[] args = joinpoint.getArgs();
        String token = (String) args[0];
        if (token != null){
            Map<String, Object> map = null;
            try {
                map = JwtUtil.analysis(token);
                ThreadLocalUtil.mapThreadLocalOfJWT.get().put("userinfo",map);
                //获取登入者id
                String strId = (String) map.get("id");
                Long id = Long.valueOf(strId);
            } catch (Exception e) {
                ThreadLocalUtil.mapThreadLocal.get().put("error","身份验证过期");
                ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.authenticationExpired);
            }
        }
        return null;
    }

    /**
     * 获取比赛题目aop，主要负责鉴权是否报名此比赛，是否拥有资格获取此题库
     * @param joinpoint
     * @return
     * @throws Exception
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.CompetitionQuestionBankController.getQuestionListInContest(..))")
    public String getQuestionListInContest(JoinPoint joinpoint) throws Exception {
        //查看是否身份验证过期
        Object[] args = joinpoint.getArgs();
        String token = (String) args[0];
        Map<String, Object> map = null;
        if (token != null){
            try {
                map = JwtUtil.analysis(token);
                ThreadLocalUtil.mapThreadLocalOfJWT.get().put("userinfo",map);
            } catch (Exception e) {
                throw new Exception("身份验证过期");

            }
        }

        //查看是否拥有获取比赛题目的资格
        Long contestId = (Long) args[1];
        CompetitionInfo competitionInfo = new CompetitionInfo();
        competitionInfo.setUserId(Long.valueOf((String) map.get("id")));
        competitionInfo.setContestId(contestId);
        CompetitionInfo competitionInfoInDB = (CompetitionInfo) redisUtil.getStringInRedis(RedisKey.ZuiOJConetstCompetitionInfo + contestId + ":" + competitionInfo.getUserId());

        if (competitionInfoInDB == null){
            competitionInfoInDB = competitionInfoMapper.checkForEntry(competitionInfo);
            if (competitionInfoInDB != null){
                redisUtil.setStringInRedis(RedisKey.ZuiOJConetstCompetitionInfo + contestId + ":" +  competitionInfo.getUserId(),RedisKey.OutTime,competitionInfoInDB);
            }
        }

        if (competitionInfoInDB == null) {
            //todo 用户无比赛权限
            ThreadLocalUtil.mapThreadLocal.get().put("error", StatusCodeMessage.NotAuthorizedToContest);
            ThreadLocalUtil.mapThreadLocal.get().put("code",StatusCode.NotAuthorizedToContest);

            return null;
        }


        return null;
    }


    /**
     * 获取比赛题目aop，主要负责鉴权是否报名此比赛，是否拥有管理员权限
     * @param joinpoint
     * @return
     * @throws Exception
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.CompetitionQuestionBankController.adminGetQuestionListInContest(..))")
    public String adminGetQuestionListInContest(JoinPoint joinpoint) throws Exception{
        //鉴权
        Object[] args = joinpoint.getArgs();
        String token = (String) args[0];
        if (token != null){
            Map<String, Object> map = null;
            try {
                map = JwtUtil.analysis(token);
                ThreadLocalUtil.mapThreadLocalOfJWT.get().put("userinfo",map);
                //获取登入者id
                String strId = (String) map.get("id");
                Long id = Long.valueOf(strId);
                //查看是否为管理员
                User user = userService.queryUserById(id);
                System.err.println(user.getRole() < 2);
                if (user.getRole() < 2){
                    ThreadLocalUtil.mapThreadLocal.get().put("error","权限不足");
                    ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.insufficientPermissions);
                }
            } catch (Exception e) {
                ThreadLocalUtil.mapThreadLocal.get().put("error","身份验证过期");
                ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.authenticationExpired);
            }
        }



        return null;
    }
}
