package com.dazuizui.business.aop.blog;

import com.dazuizui.basicapi.entry.bo.GetArticleByIdBo;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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


    /**
     * aop切面负责解析token，如果token为null则不做任何处理
     * @param joinpoint
     * @return
     * @throws Exception
     */
    public String getArticleById(JoinPoint joinpoint) throws Exception;

    /**
     * 管理员分页获取博文
     * @param joinpoint
     * @return
     */
    public String adminGetArticleByPagin(JoinPoint joinpoint) throws Exception;
}
