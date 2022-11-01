package com.dazuizui.business.aop.onlineJudge;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;


@Component
public interface ConTestServiceAop {
    public String postContest(JoinPoint joinpoint) throws Exception;
}
