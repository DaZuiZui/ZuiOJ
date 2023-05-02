package com.dazuizui.business.aop.system;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

@Component
public interface SystemAop {
    /**
     * 删除全部的ThreadLocal
     * @param joinpoint
     * @throws Exception
     */
    public void removeAllThreadLocal(JoinPoint joinpoint) throws Exception;
}
