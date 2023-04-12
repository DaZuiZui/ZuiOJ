package com.dazuizui.business.aop.proctor;

import com.dazuizui.business.domain.bo.AddProctorBo;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public interface ProctorAop {
    /**
     * 添加一个面试官Aop 前置切面，主要负责了鉴别是否为管理员
     * @param joinpoint
     * @return
     */
    public void addProctor(JoinPoint joinpoint) throws Exception;
}
