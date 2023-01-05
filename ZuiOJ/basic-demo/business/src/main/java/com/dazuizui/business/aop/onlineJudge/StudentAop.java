package com.dazuizui.business.aop.onlineJudge;


import org.aspectj.lang.JoinPoint;
import org.springframework.web.bind.annotation.RequestParam;

public interface StudentAop {
    /**
     * 学生认证切面，主要做了防止幂等性操作和身份验证
     * @param joinpoint
     * @return
     * @throws Exception
     */
    public String studentCertification(JoinPoint joinpoint) throws Exception;

    /**
     * 修改学生认证切面，主要做了防止幂等性操作和身份验证
     * @param joinpoint
     * @return
     * @throws Exception
     */
    public String updateStudentCertification(JoinPoint joinpoint) throws Exception;

    /**
     * 获取学生认证信息，
     *      aop前置环绕做了鉴权处理
     * @param joinpoint
     * @return
     */
    public void getStudentInfo(JoinPoint joinpoint) throws Exception;
}
