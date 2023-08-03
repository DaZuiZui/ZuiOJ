package com.dazuizui.business.aop.blog;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 创建比赛Aop接口
 */
/**
 * 比赛AOP接口
 */
@Aspect
@Component
public interface ContestAop {
    /**
     * 主要做了身份验证和匿等性处理
     * @param joinpoint
     * @return
     * @throws Exception
     */
    public String postContest(JoinPoint joinpoint) throws Exception;

    /**
     * @author Bryan Yang(Dazui)
     * 保证操作身份为admin
     * @param joinpoint
     * @return
     * @throws Exception
     */
    @Before("execution(* com.dazuizui.business.controller.ConTestController.removeTheContestById(..))")
    public String removeTheContestById(JoinPoint joinpoint) throws Exception;
}
