package com.dazuizui.business.aop.user;

import org.aspectj.lang.JoinPoint;

public interface TeamAop {
    public void generateGameAccountByExcel(JoinPoint joinpoint) throws Exception ;
}
