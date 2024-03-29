package com.dazuizui.business.aop.onlineJudge;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *  比赛与题库关联的Aop
 */
@Component
@Aspect
public interface CompetitionQuestionBankAop {
    /**
     * 添加比赛关联题目前置Aop
     *      主要负责身份验证和权限验证
     * @return
     */
    public String addCompetitionQuestion(JoinPoint joinpoint) throws Exception;

    /**
     * 通过contest id for get question list interface
     * @param joinpoint
     * @return
     * @throws Exception
     */
    public String getQuestionListByContestId(JoinPoint joinpoint) throws Exception;

    /**
     * 获取比赛题目aop，主要负责鉴权是否报名此比赛，是否拥有资格获取此题库
     * @param joinpoint
     * @return
     * @throws Exception
     */
    public String getQuestionListInContest(JoinPoint joinpoint) throws Exception;


    /**
     * 管理员获取比赛题目aop，主要负责鉴权是否报名此比赛，是否拥有管理员权限
     * @param joinpoint
     * @return
     * @throws Exception
     */
    public String adminGetQuestionListInContest(JoinPoint joinpoint) throws Exception;


    /**
     * 监考人员比赛题目aop，主要负责鉴权是否监考人员
     * @param joinpoint
     * @return
     * @throws Exception
     */
    public String proctorGetQuestionListInContest(JoinPoint joinpoint) throws Exception;
}
