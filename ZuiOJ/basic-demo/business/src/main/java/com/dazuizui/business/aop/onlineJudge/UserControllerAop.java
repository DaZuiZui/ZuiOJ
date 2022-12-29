package com.dazuizui.business.aop.onlineJudge;

import com.dazuizui.basicapi.entry.bo.DeleteUserByIdBo;
import com.dazuizui.basicapi.entry.bo.DeleteUsersInBulkBo;
import com.dazuizui.basicapi.entry.bo.PagingToGetUserDateBo;
import com.dazuizui.basicapi.entry.bo.TombstoneUserByIdBo;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 用户控制器切面
 * User aop controller
 */
@Component
public interface UserControllerAop {
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
}
