package com.dazuizui.business.aop.onlineJudge.impl;

import com.dazuizui.business.aop.onlineJudge.ConTestServiceAop;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


/**
 * @Author 杨易达
 * 哈尔滨广厦学院OJ系统 竞赛aop 代码增强
 */
@Aspect
@Component
public class ConTestServiceAopImpl implements ConTestServiceAop {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 非幂等性问题代码aop增强
     * @param joinpoint
     * @return
     * @throws Exception
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.ConTestController.postContest(..))")
    public String postContest(JoinPoint joinpoint) throws Exception {
        Object[] args = joinpoint.getArgs();
        String token = (String) args[0];
        boolean b = redisTemplate.delete(token);
        if (!b){
            throw new Exception("idempotency");
        }
        return null;
    }
}
