package com.dazuizui.business.aop.onlineJudge.impl;

import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.bo.StudentCertificationBo;
import com.dazuizui.business.aop.onlineJudge.StudentAop;
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
 *   学生控制器切面
 */
@Component
@Aspect
public class StudentAopImpl implements StudentAop {
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 学生认证切面，主要做了防止幂等性操作和身份验证
     * @param joinpoint
     * @return
     * @throws Exception
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.StudentController.studentCertification(..))")
    public String studentCertification(JoinPoint joinpoint) throws Exception {
        Object[] args = joinpoint.getArgs();
        //System.err.println(args.length);
        StudentCertificationBo studentCertificationBo =  (StudentCertificationBo) args[0];
        //System.err.println(studentCertificationBo);

        /**
         * 防止非幂等性
         */
        String idemtoken = studentCertificationBo.getIdemtoken();
        System.err.println(idemtoken);
        boolean b = redisTemplate.delete(idemtoken);
        if (!b){
            ThreadLocalUtil.mapThreadLocal.get().put("error","异常幂等性操作，请刷新网页重新操作");
            ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.Idempotency);
        }

        /**
         * 身份鉴权
         */
        Map<String, Object> map = null;
        String token = studentCertificationBo.getToken();
        //System.err.println("toekn si "+token);
        if (token != null){
            try {
                map = JwtUtil.analysis(token);
                ThreadLocalUtil.mapThreadLocalOfJWT.get().put("userinfo",map);
            } catch (Exception e) {

                ThreadLocalUtil.mapThreadLocal.get().put("error","身份验证过期");
                ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.authenticationExpired);
                return null;
            }
        }

        return null;
    }


    /**
     * 获取学生认证信息，
     *      aop前置环绕做了鉴权处理
     * @param joinpoint
     * @return
     */
    @Override
    public void getStudentInfo(JoinPoint joinpoint) throws Exception {
        Object[] args = joinpoint.getArgs();
        String token  = (String) args[0];
        Map<String, Object> map = null;
        if (token != null){
            try {
                map = JwtUtil.analysis(token);
                ThreadLocalUtil.mapThreadLocalOfJWT.get().put("userinfo",map);
            } catch (Exception e) {

                ThreadLocalUtil.mapThreadLocal.get().put("error","身份验证过期");
                ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.authenticationExpired);
            }
        }
    }
}
