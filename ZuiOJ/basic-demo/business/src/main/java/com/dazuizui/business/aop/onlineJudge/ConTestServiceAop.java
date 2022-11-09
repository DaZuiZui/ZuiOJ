package com.dazuizui.business.aop.onlineJudge;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;


@Component
public interface ConTestServiceAop {
    /**
     * 提交竞赛
     * @param joinpoint
     * @return
     * @throws Exception
     */
    public String postContest(JoinPoint joinpoint) throws Exception;

    /**
     * 报名aop
     * @param joinpoint
     * @return
     * @throws Exception
     */
    public String signUpForTheCompetition(JoinPoint joinpoint) throws Exception;
}
