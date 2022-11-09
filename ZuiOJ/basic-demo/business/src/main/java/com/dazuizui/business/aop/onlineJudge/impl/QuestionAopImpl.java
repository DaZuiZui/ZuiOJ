package com.dazuizui.business.aop.onlineJudge.impl;

import com.dazuizui.basicapi.entry.bo.QuestionBankBo;
import com.dazuizui.business.aop.onlineJudge.QuestionAop;
import com.dazuizui.business.util.JwtUtil;
import com.dazuizui.business.util.ThreadLocalUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Aspect
@Component
public class QuestionAopImpl implements QuestionAop {
    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 通过题目id获取题目的AOP
     * @param joinpoint
     * @return
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.QuestionBankController.getQuestionById(..))")
    public String getQuestionById(JoinPoint joinpoint) {
        Object[] args = joinpoint.getArgs();
        String token = (String) args[0];
        if (token != null){
            Map<String, Object> map = null;
            try {
                map = JwtUtil.analysis(token);
                ThreadLocalUtil.mapThreadLocalOfJWT.get().put("userrinfo",map);
            } catch (Exception e) {
                return null;
            }
        }

        return null;
    }

    @Override
    @Before("execution(* com.dazuizui.business.controller.QuestionBankController.postQuestion(..))")
    public String postQuestion(JoinPoint joinpoint) throws Exception {
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
}
