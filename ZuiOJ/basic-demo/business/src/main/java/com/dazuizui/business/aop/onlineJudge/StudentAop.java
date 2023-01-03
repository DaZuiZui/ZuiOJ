package com.dazuizui.business.aop.onlineJudge;


import org.aspectj.lang.JoinPoint;

public interface StudentAop {
    /**
     * 学生认证切面，主要做了防止幂等性操作和身份验证
     * @param joinpoint
     * @return
     * @throws Exception
     */
    public String studentCertification(JoinPoint joinpoint) throws Exception;
}
