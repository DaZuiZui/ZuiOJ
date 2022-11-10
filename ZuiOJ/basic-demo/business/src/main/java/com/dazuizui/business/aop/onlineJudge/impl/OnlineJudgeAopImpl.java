package com.dazuizui.business.aop.onlineJudge.impl;

import com.dazuizui.basicapi.entry.bo.ProgramBo;
import com.dazuizui.business.aop.onlineJudge.OnlineJudgeAop;
import com.dazuizui.business.util.JwtUtil;
import com.dazuizui.business.util.ThreadLocalUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Map;


@Aspect
@Component
public class OnlineJudgeAopImpl implements OnlineJudgeAop {

    /**
     * 判决代码Aop，进行鉴权
     * @param joinpoint
     * @return
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.OnlineJudgeController.judgeTheProgram(..))")
    public String judgeTheProgram(JoinPoint joinpoint) throws Exception{
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
