package com.dazuizui.business.aop.blog.impl;

import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.bo.CreateArticleBo;
import com.dazuizui.business.aop.blog.BlogAop;
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
 * 博客Aop接口实现
 */
@Component
@Aspect
public class BlogAopImpl implements BlogAop {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 创建博文AOP，主要做了防止非幂等操作和token鉴权
     * @param joinpoint
     * @return
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.BlogController.createArticle(..))")
    public void createArticle(JoinPoint joinpoint) throws Exception{
        Object[] args = joinpoint.getArgs();
        CreateArticleBo articleBo = (CreateArticleBo) args[0];

        //1.防止非幂等性操作
        String nonPowerToken = articleBo.getNonPowerToken();
        boolean b = redisTemplate.delete(nonPowerToken);
        if (!b){
            ThreadLocalUtil.mapThreadLocal.get().put("error","异常幂等性操作，请刷新网页重新操作");
            ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.Idempotency);
            return;
        }

        //2.鉴权
        Map<String, Object> map = null;
        String token = articleBo.getToken();
        if (token != null){
            try {
                map = JwtUtil.analysis(token);
                ThreadLocalUtil.mapThreadLocalOfJWT.get().put("userinfo",map);
                //System.err.println("????????");
            } catch (Exception e) {
                //System.out.println("??");
                ThreadLocalUtil.mapThreadLocal.get().put("error","身份验证过期");
                ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.authenticationExpired);
                return ;
            }
        }
    }

    /**
     * 创建题解
     * @param joinpoint
     * @return
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.BlogController.createQuestionAnswer(..))")
    public void createQuestionAnswer(JoinPoint joinpoint) throws Exception{
        Object[] args = joinpoint.getArgs();
        CreateArticleBo articleBo = (CreateArticleBo) args[0];

        //1.防止非幂等性操作
        String nonPowerToken = articleBo.getNonPowerToken();
        boolean b = redisTemplate.delete(nonPowerToken);
        if (!b){
            ThreadLocalUtil.mapThreadLocal.get().put("error","异常幂等性操作，请刷新网页重新操作");
            ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.Idempotency);
            return;
        }

        //2.鉴权
        Map<String, Object> map = null;
        String token = articleBo.getToken();
        if (token != null){
            try {
                map = JwtUtil.analysis(token);
                ThreadLocalUtil.mapThreadLocalOfJWT.get().put("userinfo",map);
                //System.err.println("????????");
            } catch (Exception e) {
                //System.out.println("??");
                ThreadLocalUtil.mapThreadLocal.get().put("error","身份验证过期");
                ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.authenticationExpired);
                return ;
            }
        }
    }
}
