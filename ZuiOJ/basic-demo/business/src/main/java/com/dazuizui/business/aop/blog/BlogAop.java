package com.dazuizui.business.aop.blog;

import com.dazuizui.basicapi.entry.bo.GetArticleByIdBo;
import com.dazuizui.business.domain.bo.AdminDeleteAritcleByIdBo;
import com.dazuizui.business.domain.bo.CreateArticleBo;
import com.dazuizui.business.domain.bo.PhysicallyDeleteArticlesBo;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 博客Aop接口实现
 */
@Aspect
@Component
public interface BlogAop {
    @Before("execution(* com.dazuizui.business.controller.BlogController.browseArticleById(..))")
    public void browseArticleById(JoinPoint joinPoint) throws Exception;

    /**
     * 用户逻辑删除博文通过Id
     * @return
     */
    @Before("execution(* com.dazuizui.business.controller.BlogController.userDeleteAritcleById(..))")
    public String userDeleteAritcleById(JoinPoint joinpoint) throws Exception;

    /**
     * 用户鉴权获取自己的token
     * todo 改成用户解析
     * @param joinpoint
     * @return
     * @throws Exception
     */
    @Before("execution(* com.dazuizui.business.controller.BlogController.userGetMyselfArticle(..))")
    public String userGetMyselfArticle(JoinPoint joinpoint) throws Exception;

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


    /**
     * 管理员批量删除博文aop
     *      主要负责管理权限的鉴权
     * @param joinpoint
     * @return
     * @throws Exception
     */
    public String physicallyDeleteArticles(JoinPoint joinpoint) throws Exception;


    /**
     * 管理员或者作者修改博文通过博文id
     * @param joinpoint
     * @return
     * @throws Exception
     */
    @Before("execution(* com.dazuizui.business.controller.BlogController.updateArticleByid(..))")
    public String updateArticleByid(JoinPoint joinpoint) throws Exception;
}
