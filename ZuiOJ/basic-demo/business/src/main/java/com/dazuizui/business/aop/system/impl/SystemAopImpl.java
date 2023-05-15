package com.dazuizui.business.aop.system.impl;

import com.dazuizui.business.aop.system.SystemAop;
import com.dazuizui.business.util.ThreadLocalUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SystemAopImpl implements SystemAop {
    @Override
    @After("execution(* com.dazuizui.business.controller.*.*(..))")
    public void removeAllThreadLocal(JoinPoint joinpoint) throws Exception {
        ThreadLocalUtil.mapThreadLocalOfJWT.remove();
        ThreadLocalUtil.mapThreadLocal.remove();
        ThreadLocalUtil.DataOfThreadLocal.remove();

    }
}
