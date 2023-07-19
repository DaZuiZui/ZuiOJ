package com.dazuizui.business.aop.onlineJudge;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 用户控制器切面
 * User aop controller
 */
@Component
@Aspect
public interface UserControllerAop {
    /**
     * 查询网站管理人员 主要查看是否为管理员权限
     * @return
     */
    public String queryListOfAdmin(JoinPoint joinpoint) throws Exception;

    /**
     * 用户切面aop
     *      鉴权是权限是否足够，登入验证身份是否过期
     * @param joinpoint
     * @return
     */
    public String deleteUsersInBulk(JoinPoint joinpoint) throws Exception;

    /**
     * 逻辑删除通过Id
     *      鉴权是权限是否足够，登入验证身份是否过期
     * @param joinpoint
     * @return
     * @throws Exception
     */
    public String tombstoneUserById(JoinPoint joinpoint) throws Exception;

    /**
     * 管理员分页获取数据
     *      鉴权是权限是否足够，登入验证身份是否过期
     * @param joinpoint
     * @return
     * @throws Exception
     */
    public String pagingToGetUserDate(JoinPoint joinpoint) throws Exception;

    /**
     * 通过id物理删除用户
     *      鉴权是权限是否足够，登入验证身份是否过期
     * @param joinpoint
     * @return
     * @throws Exception
     */
    public String deleteUserById(JoinPoint joinpoint) throws Exception;

    /**
     * 管理员获取用户信息
     *      主要做了是否存在管理员权限
     * @param joinpoint
     * @return
     * @throws Exception
     */
    @Before("execution(* com.dazuizui.business.controller.UserController.adminGetUserInfo(..))")
    public String adminGetUserInfo(JoinPoint joinpoint) throws Exception;

    /**
     * 管理员修改用户信息
     *      主要做了是否存在管理员权限
     * @param joinpoint
     * @return
     * @throws Exception
     */
    @Before("execution(* com.dazuizui.business.controller.UserController.adminUpdateUserInfoById(..))")
    public String adminUpdateUserInfoById(JoinPoint joinpoint) throws Exception;
}
