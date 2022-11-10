package com.dazuizui.business.aop.onlineJudge.impl;

import com.dazuizui.business.aop.onlineJudge.ConTestServiceAop;
import com.dazuizui.business.util.JwtUtil;
import com.dazuizui.business.util.ThreadLocalUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;


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

    /**
     * 幂等性问题代码aop增强
     * @param joinpoint
     * @return
     * @throws Exception
     */

    @Before("execution(* com.dazuizui.business.controller.ConTestController.signUpForTheCompetition(..))")
    public String signUpForTheCompetition(JoinPoint joinpoint) throws Exception {
        //幂等性问题
        Object[] args = joinpoint.getArgs();
        String Idemtoken = (String) args[0];
        boolean b = redisTemplate.delete(Idemtoken);
        if (!b){
            throw new Exception("idempotency");
        }

        //鉴权
        String token = (String) args[1];
        if (token != null){
            Map<String, Object> map = null;
            try {
                map = JwtUtil.analysis(token);
                ThreadLocalUtil.mapThreadLocalOfJWT.get().put("userinfo",map);
            } catch (Exception e) {
                throw new Exception("身份验证过期");

            }
        }

        return null;
    }

    /**
     * 通过id获取赛事aop
     * @param joinpoint
     * @return
     * @throws Exception
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.ConTestController.getEventById(..))")
    public String getEventById(JoinPoint joinpoint) throws Exception {
        //鉴权
        Object[] args = joinpoint.getArgs();
        String token = (String) args[0];
        if (token != null){
            Map<String, Object> map = null;
            try {
                map = JwtUtil.analysis(token);
                ThreadLocalUtil.mapThreadLocalOfJWT.get().put("userinfo",map);
            } catch (Exception e) {
                throw new Exception("身份验证过期");

            }
        }


        return null;
    }


}
