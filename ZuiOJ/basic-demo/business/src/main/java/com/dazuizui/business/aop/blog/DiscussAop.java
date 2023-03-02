package com.dazuizui.business.aop.blog;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

/**
 * 评论aop接口
 */
@Component
public interface DiscussAop {
    /**
     * 负责提交评论的鉴权
     * @param joinpoint
     * @return
     */
    public void submitDiscuss(JoinPoint joinpoint) throws Exception;
}
