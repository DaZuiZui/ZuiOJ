package com.dazuizui.business.aop.dc.impl;

import com.dazuizui.business.aop.dc.DCAop;
import com.dazuizui.business.domain.bo.StartCheckingBo;
import com.dazuizui.business.service.system.SystemVerifyService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Aspect
public class DCAopImpl implements DCAop {
    @Autowired
    private SystemVerifyService systemVerifyService;
    /**
     * 开始查重AOP
     *    主要做了管理员权限验证和幂等性处理
     * @param joinpoint
     * @return
     * @throws Exception
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.DCController.startChecking(..))")
    public String startChecking(JoinPoint joinpoint) throws Exception {
        System.err.println("qwdguyqgdugqysgsyuqgsygdquy");
        Object[] args = joinpoint.getArgs();
        StartCheckingBo startCheckingBos = (StartCheckingBo) args[0];
        String token = startCheckingBos.getToken();
        String nonPowerToken = startCheckingBos.getNonPowerToken();
        systemVerifyService.verfiNonPowerTokenAndAdminToken(nonPowerToken,token,2);

        return null;
    }
}
