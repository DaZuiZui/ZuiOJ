package com.dazuizui.business.aop.onlineJudge.impl;

import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.User;
import com.dazuizui.basicapi.entry.bo.AddQuestionCaseBo;
import com.dazuizui.basicapi.entry.bo.StudentCertificationBo;
import com.dazuizui.business.aop.onlineJudge.QuestionCaseAop;
import com.dazuizui.business.domain.bo.UpdateQuestionCaseBo;
import com.dazuizui.business.service.user.UserService;
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
    @Autowired
    private UserService userService;


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

    /**
     * 修改案例Aop
     *      主要负责鉴权是否为管理员
     * @param joinpoint
     * @throws Exception
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.QuestionCaseController.updateQuestionCase(..))")
    public void UpdateQuestionCase(JoinPoint joinpoint) throws Exception {
        Object[] args = joinpoint.getArgs();
        UpdateQuestionCaseBo updateQuestionCaseBo = (UpdateQuestionCaseBo) args[0];
        String token = updateQuestionCaseBo.getToken();

        if (token != null){
            Map<String, Object> map = null;
            try {
                map = JwtUtil.analysis(token);
                ThreadLocalUtil.mapThreadLocalOfJWT.get().put("userinfo",map);
                //获取登入者id
                String strId = (String) map.get("id");
                Long id = Long.valueOf(strId);
                //查看是否为管理员
                User user = userService.queryUserById(id);
                System.err.println(user.getRole() < 2);
                if (user.getRole() < 2){
                    ThreadLocalUtil.mapThreadLocal.get().put("error","权限不足");
                    ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.insufficientPermissions);
                }
            } catch (Exception e) {
                ThreadLocalUtil.mapThreadLocal.get().put("error","身份验证过期");
                ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.authenticationExpired);
            }
        }


        return ;
    }

    /**
     * 通过案例id删除案例Aop
     *  主要负责鉴权是否拥有管理员权限
     * @param joinpoint
     * @return
     * @throws Exception
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.QuestionCaseController.deleteCaseByCaseId(..))")
    public String deleteCaseByCaseId(JoinPoint joinpoint) throws Exception {
        Object[] args = joinpoint.getArgs();
        String token = (String) args[0];

        if (token != null){
            Map<String, Object> map = null;
            try {
                map = JwtUtil.analysis(token);
                ThreadLocalUtil.mapThreadLocalOfJWT.get().put("userinfo",map);
                //获取登入者id
                String strId = (String) map.get("id");
                Long id = Long.valueOf(strId);
                //查看是否为管理员
                User user = userService.queryUserById(id);
                System.err.println(user.getRole() < 2);
                if (user.getRole() < 2){
                    ThreadLocalUtil.mapThreadLocal.get().put("error","权限不足");
                    ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.insufficientPermissions);
                }
            } catch (Exception e) {
                ThreadLocalUtil.mapThreadLocal.get().put("error","身份验证过期");
                ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.authenticationExpired);
            }
        }

        return null;
    }
}
