package com.dazuizui.business.aop.blog;

import org.aspectj.lang.JoinPoint;

/**
 * 创建比赛Aop接口
 */
public interface ContestAop {
    /**
     * 主要做了身份验证和匿等性处理
     * @param joinpoint
     * @return
     * @throws Exception
     */
    public String postContest(JoinPoint joinpoint) throws Exception;
}
