package com.dazuizui.business.aop.onlineJudge.impl;

import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.User;
import com.dazuizui.basicapi.entry.bo.*;
import com.dazuizui.business.aop.onlineJudge.UserControllerAop;
import com.dazuizui.business.service.user.UserService;
import com.dazuizui.business.util.JwtUtil;
import com.dazuizui.business.util.ThreadLocalUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 用户控制器切面实现
 * User aop controller impl
 */
@Component
@Aspect
public class UserControllerAopImpl implements UserControllerAop {
    @Autowired
    private UserService userService;

    /**
     * 查询管理员人员，查询是否为管理员身份
     * @param joinpoint
     * @return
     * @throws Exception
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.UserController.queryListOfAdmin(..))")
    public String queryListOfAdmin(JoinPoint joinpoint) throws Exception{
        Object[] args = joinpoint.getArgs();
        ;
        Map<String, Object> map = null;
        String token = (String) args[0];
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
        // System.err.println("??"+map);
        Long id = Long.valueOf((String) map.get("id"));
        User user = userService.queryUserById(id);
        Integer role = user.getRole();
        if (role < 4){
            ThreadLocalUtil.mapThreadLocal.get().put("error","权限不足");
            ThreadLocalUtil.mapThreadLocal.get().put("code",StatusCode.insufficientPermissions);
            return null;
        }

        return null;
    }

    /**
     * 批量逻辑删除aop
     * @param joinpoint
     * @return
     * @throws Exception
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.UserController.deleteUsersInBulk(..))")
    public String deleteUsersInBulk(JoinPoint joinpoint) throws Exception {
        //鉴权
        Object[] args = joinpoint.getArgs();
        DeleteUsersInBulkBo arg = (DeleteUsersInBulkBo) args[0];
        Map<String, Object> map = null;
        String token = arg.getToken();
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
       // System.err.println("??"+map);
        Long id = Long.valueOf((String) map.get("id"));
        User user = userService.queryUserById(id);
        Integer role = user.getRole();
        if (role < 2){
            ThreadLocalUtil.mapThreadLocal.get().put("error","权限不足");
            ThreadLocalUtil.mapThreadLocal.get().put("code",StatusCode.insufficientPermissions);
            return null;
        }

        return null;
    }

    /**
     * 逻辑删除通过id
     *      鉴权是否有个管理员权限和是有登入
     * @param joinpoint
     * @return
     * @throws Exception
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.UserController.tombstoneUserById(..))")
    public String tombstoneUserById(JoinPoint joinpoint) throws Exception {
        //鉴权
        Object[] args = joinpoint.getArgs();
        TombstoneUserByIdBo arg = (TombstoneUserByIdBo) args[0];
        Map<String, Object> map = null;
        String token = arg.getToken();
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
        //System.err.println("??"+map);
        Long id = Long.valueOf((String) map.get("id"));
        User user = userService.queryUserById(id);
        Integer role = user.getRole();
        if (role < 2){
            ThreadLocalUtil.mapThreadLocal.get().put("error","权限不足");
            ThreadLocalUtil.mapThreadLocal.get().put("code",StatusCode.insufficientPermissions);
            return null;
        }

        return null;
    }

    /**
     * 管理员分页获取数据
     *      鉴权是权限是否足够，登入验证身份是否过期
     * @param joinpoint
     * @return
     * @throws Exception
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.UserController.pagingToGetUserDate(..))")
    public String pagingToGetUserDate(JoinPoint joinpoint) throws Exception{
        //鉴权
        Object[] args = joinpoint.getArgs();
        PagingToGetUserDateBo arg = (PagingToGetUserDateBo) args[0];
        Map<String, Object> map = null;
        String token = arg.getToken();
        //System.err.println("toekn si "+token);
        if (token != null){
            try {
                map = JwtUtil.analysis(token);
                ThreadLocalUtil.mapThreadLocalOfJWT.get().put("userinfo",map);
            } catch (Exception e) {
              //  e.printStackTrace();
                ThreadLocalUtil.mapThreadLocal.get().put("error","身份验证过期");
                ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.authenticationExpired);
                return null;
            }
        }
        //System.err.println("??"+map);
        Long id = Long.valueOf((String) map.get("id"));
        User user = userService.queryUserById(id);
        Integer role = user.getRole();
        if (role < 2){
            ThreadLocalUtil.mapThreadLocal.get().put("error","权限不足");
            ThreadLocalUtil.mapThreadLocal.get().put("code",StatusCode.insufficientPermissions);
            return null;
        }

        return null;
    }

    /**
     * 通过id物理删除用户
     *      鉴权是权限是否足够，登入验证身份是否过期
     * @param joinpoint
     * @return
     * @throws Exception
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.UserController.deleteUserById(..))")
    public String deleteUserById(JoinPoint joinpoint) throws Exception {
        //鉴权
        Object[] args = joinpoint.getArgs();
        DeleteUserByIdBo arg = (DeleteUserByIdBo) args[0];
        Map<String, Object> map = null;
        String token = arg.getToken();
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
        System.err.println("??"+map);
        Long id = Long.valueOf((String) map.get("id"));
        User user = userService.queryUserById(id);
        Integer role = user.getRole();
        if (role < 2){
            ThreadLocalUtil.mapThreadLocal.get().put("error","权限不足");
            ThreadLocalUtil.mapThreadLocal.get().put("code",StatusCode.insufficientPermissions);
            return null;
        }

        return null;
    }
}
