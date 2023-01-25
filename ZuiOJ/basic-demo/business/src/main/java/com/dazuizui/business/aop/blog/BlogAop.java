package com.dazuizui.business.aop.blog;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

/**
 * 博客Aop接口实现
 */
@Component
public interface BlogAop {

    /**
     * 创建博文AOP，主要做了防止非幂等操作和token鉴权
     * @param joinpoint
     * @return
     */
    public void createArticle(JoinPoint joinpoint) throws Exception;

    /**
     * 创建题解
     * @param joinpoint
     * @return
     */
    public void createQuestionAnswer(JoinPoint joinpoint) throws Exception;
}
