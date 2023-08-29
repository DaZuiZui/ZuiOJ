package com.dazuizui.business.aop.dc;

import com.dazuizui.business.domain.bo.StartCheckingBo;
import org.aspectj.lang.JoinPoint;
import org.springframework.web.bind.annotation.RequestBody;

public interface DCAop {

    /**
     * 开始查重AOP
     *    主要做了管理员权限验证和幂等性处理
     * @param joinpoint
     * @return
     * @throws Exception
     */
    public String startChecking(JoinPoint joinpoint) throws Exception;
}
