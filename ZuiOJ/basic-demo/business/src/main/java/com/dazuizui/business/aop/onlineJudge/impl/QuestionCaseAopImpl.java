package com.dazuizui.business.aop.onlineJudge.impl;

import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.bo.AddQuestionCaseBo;
import com.dazuizui.basicapi.entry.bo.StudentCertificationBo;
import com.dazuizui.business.aop.onlineJudge.QuestionCaseAop;
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
 * 案例Aop接口实现
 */
@Aspect
@Component
public class QuestionCaseAopImpl implements QuestionCaseAop {
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 添加问题案例
     * @param joinpoint
     * @return
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.QuestionCaseController.addQuestionCase(..))")
    public void addQuestionCase(JoinPoint joinpoint) throws Exception {
        Object[] args = joinpoint.getArgs();
        AddQuestionCaseBo addQuestionCaseBos = (AddQuestionCaseBo) args[0];

        /**
         * 防止非幂等性
         */
        String idemtoken = addQuestionCaseBos.getNonPowerToken();
        boolean b = redisTemplate.delete(idemtoken);
        if (!b){
            ThreadLocalUtil.mapThreadLocal.get().put("error","异常幂等性操作，请刷新网页重新操作");
            ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.Idempotency);
        }

        /**
         * 身份鉴权
         */
        Map<String, Object> map = null;
        String token = addQuestionCaseBos.getToken();
        //System.err.println("toekn si "+token);
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

        return;
    }
}
