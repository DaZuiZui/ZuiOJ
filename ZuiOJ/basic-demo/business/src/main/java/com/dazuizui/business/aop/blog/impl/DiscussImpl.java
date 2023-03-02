package com.dazuizui.business.aop.blog.impl;

import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.bo.CreateArticleBo;
import com.dazuizui.business.aop.blog.DiscussAop;
import com.dazuizui.business.domain.bo.SubmitDiscussBo;
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
 * 评论aop切面
 */
@Component
@Aspect
public class DiscussImpl implements DiscussAop {
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     *  提交评论
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.QuestionDiscussController.submitDiscuss(..))")
    public void submitDiscuss(JoinPoint joinpoint) throws Exception {
        Object[] args = joinpoint.getArgs();
        SubmitDiscussBo submitDiscussBo = (SubmitDiscussBo) args[0];

        //1.防止非幂等性操作
        String nonPowerToken = submitDiscussBo.getNonPowerToken();
        boolean b = redisTemplate.delete(nonPowerToken);
        if (!b){
            ThreadLocalUtil.mapThreadLocal.get().put("error","异常幂等性操作，请刷新网页重新操作");
            ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.Idempotency);
            return;
        }

        //2.鉴权
        Map<String, Object> map = null;
        String token = submitDiscussBo.getToken();
        if (token != null){
            try {
                map = JwtUtil.analysis(token);
                ThreadLocalUtil.mapThreadLocalOfJWT.get().put("userinfo",map);
            } catch (Exception e) {
                ThreadLocalUtil.mapThreadLocal.get().put("error","身份验证过期");
                ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.authenticationExpired);
                return ;
            }
        }
        return ;
    }
}
