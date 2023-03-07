package com.dazuizui.business.aop.onlineJudge;

import com.dazuizui.basicapi.entry.bo.DeleteUserByIdBo;
import com.dazuizui.basicapi.entry.bo.DeleteUsersInBulkBo;
import com.dazuizui.basicapi.entry.bo.PagingToGetUserDateBo;
import com.dazuizui.basicapi.entry.bo.TombstoneUserByIdBo;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户控制器切面
 * User aop controller
 */
@Component
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
}
