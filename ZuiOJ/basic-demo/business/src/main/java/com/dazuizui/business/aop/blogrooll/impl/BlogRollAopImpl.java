package com.dazuizui.business.aop.blogrooll.impl;

import com.dazuizui.basicapi.entry.Blogroll;
import com.dazuizui.business.aop.blogrooll.BlogRollAop;
import com.dazuizui.business.service.system.SystemVerifyService;
import com.dazuizui.business.util.ThreadLocalUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Aspect
@Component
public class BlogRollAopImpl implements BlogRollAop {

    @Autowired
    private SystemVerifyService systemVerifyService;

    /**
     * @author Bryan Yang(Dazui) 16/8/2023
     * 申请友情连接
     * @param joinpoint
     * @return
     */
    @Before("execution(* com.dazuizui.business.controller.BlogrollController.applyForBlogRoll(..))")
    public void applyForBlogRoll(JoinPoint joinpoint){
        Object[] args = joinpoint.getArgs();
        String nonPowerToken = (String) args[0];
        String token = (String) args[1];
        System.err.println("666");
        systemVerifyService.veryfiUserAndNonPowerToken(nonPowerToken,token);

        System.out.println(ThreadLocalUtil.mapThreadLocalOfJWT.get());
    }
}
