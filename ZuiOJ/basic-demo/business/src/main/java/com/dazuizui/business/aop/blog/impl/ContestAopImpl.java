package com.dazuizui.business.aop.blog.impl;

import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.bo.CreateArticleBo;
import com.dazuizui.business.aop.blog.ContestAop;
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
 * 比赛AOP接口
 */
@Aspect
@Component
public class ContestAopImpl implements ContestAop {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 提交比赛AOP接口实现，主要做了身份验证和幂等性操作
     * @param joinpoint
     * @return
     * @throws Exception
     */
    @Override
   // @Before("execution(* com.dazuizui.business.controller.ConTestController.postContest(..))")
    public String postContest(JoinPoint joinpoint) throws Exception {
        Object[] args = joinpoint.getArgs();
        String nonPowerToken = (String) args[0];
        //1.防止非幂等性操作
        boolean b = redisTemplate.delete(nonPowerToken);
        if (!b){
            ThreadLocalUtil.mapThreadLocal.get().put("error","异常幂等性操作，请刷新网页重新操作");
            ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.Idempotency);
            return null;
        }

        //2.鉴权
        Map<String, Object> map = null;
        String token =  (String) args[1];
        if (token != null){
            try {
                map = JwtUtil.analysis(token);
                ThreadLocalUtil.mapThreadLocalOfJWT.get().put("userinfo",map);
            } catch (Exception e) {
                ThreadLocalUtil.mapThreadLocal.get().put("error","身份验证过期");
                ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.authenticationExpired);
                return null;
            }
        }
        return null;
    }
}
