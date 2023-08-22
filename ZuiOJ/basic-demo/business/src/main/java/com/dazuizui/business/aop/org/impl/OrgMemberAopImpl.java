package com.dazuizui.business.aop.org.impl;

import com.dazuizui.business.aop.org.OrgMemberAop;
import com.dazuizui.business.service.system.SystemVerifyService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class OrgMemberAopImpl implements OrgMemberAop {

    @Autowired
    private SystemVerifyService systemVerifyService;

    /**
     * @author Bryan yang 22/8/2023
     *
     * 插入组织成员
     *    主要做了幂等性处理
     * @param joinpoint
     *
     * @return
     * @throws Exception
     */
    @Before("execution(* com.dazuizui.business.controller.OrgMemberController.insertMember(..))")
    @Override
    public void insertMember(JoinPoint joinpoint) throws Exception {
        Object[] args = joinpoint.getArgs();
        String token  = (String) args[0];
        systemVerifyService.verfiNonPowerToken(token);
    }
}
