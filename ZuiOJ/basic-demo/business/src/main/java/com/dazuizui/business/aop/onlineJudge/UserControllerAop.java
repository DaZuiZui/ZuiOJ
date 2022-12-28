package com.dazuizui.business.aop.onlineJudge;

import com.dazuizui.basicapi.entry.bo.DeleteUsersInBulkBo;
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
     * @param joinpoint
     * @return
     */
    public String deleteUsersInBulk(JoinPoint joinpoint) throws Exception;
}
