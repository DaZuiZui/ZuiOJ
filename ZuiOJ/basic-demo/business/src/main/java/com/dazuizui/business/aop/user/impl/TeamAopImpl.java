package com.dazuizui.business.aop.user.impl;

import com.dazuizui.business.aop.user.TeamAop;
import com.dazuizui.business.domain.bo.PhysicallyDeleteArticlesBo;
import com.dazuizui.business.service.system.SystemVerifyService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TeamAopImpl implements TeamAop {
    @Autowired
    private SystemVerifyService systemVerifyService;

    /**
     * 通过excel生成比赛账户，该aop主要做了鉴权是否为管理员
     * @param joinpoint
     * @throws Exception
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.TeamController.generateMatchTeamsByExcel(..))")
    public void generateGameAccountByExcel(JoinPoint joinpoint) throws Exception {
        Object[] args = joinpoint.getArgs();
        String token = (String) args[1];
        systemVerifyService.veryfiAdmin(token,2);
    }
}
