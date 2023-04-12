package com.dazuizui.business.aop.proctor.impl;

import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.User;
import com.dazuizui.business.aop.proctor.ProctorAop;
import com.dazuizui.business.domain.bo.AddProctorBo;
import com.dazuizui.business.service.user.UserService;
import com.dazuizui.business.util.JwtUtil;
import com.dazuizui.business.util.ThreadLocalUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
@Aspect
public class ProctorAopImpl implements ProctorAop {
    @Autowired
    private UserService userService;
    /**
     * 添加一个面试官Aop 前置切面，主要负责了鉴别是否为管理员
     * @param joinpoint
     * @return
     */
    @Before("execution(* com.dazuizui.business.controller.ProctorController.addProctor(..))")
    public void addProctor(JoinPoint joinpoint) throws Exception{
        Object[] args = joinpoint.getArgs();
        AddProctorBo addProctorBo = (AddProctorBo) args[0];
        String token = addProctorBo.getToken();
        System.err.println(token);
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

                if (user.getRole() < 2){
                    ThreadLocalUtil.mapThreadLocal.get().put("error","权限不足");
                    ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.insufficientPermissions);
                }

            } catch (Exception e) {
                ThreadLocalUtil.mapThreadLocal.get().put("error","身份验证过期");
                ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.authenticationExpired);
            }
        }
    }
}
