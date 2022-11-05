package com.dazuizui.business.aop.onlineJudge.impl;

import com.dazuizui.business.aop.onlineJudge.QuestionAop;
import com.dazuizui.business.util.JwtUtil;
import com.dazuizui.business.util.ThreadLocalUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Map;

@Aspect
@Component
public class QuestionAopImpl implements QuestionAop {

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
}
