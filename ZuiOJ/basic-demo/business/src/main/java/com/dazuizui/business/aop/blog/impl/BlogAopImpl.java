package com.dazuizui.business.aop.blog.impl;

import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.business.domain.bo.*;
import com.dazuizui.basicapi.entry.bo.GetArticleByIdBo;
import com.dazuizui.business.aop.blog.BlogAop;
import com.dazuizui.business.service.system.SystemVerifyService;
import com.dazuizui.business.util.JwtUtil;
import com.dazuizui.business.util.ThreadLocalUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 博客Aop接口实现
 */
@Component
@Aspect
public class BlogAopImpl implements BlogAop {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SystemVerifyService systemVerifyService;

    @Override
    public String userDeleteAritcleById(JoinPoint joinpoint) throws Exception {
        Object[] args = joinpoint.getArgs();
        AdminDeleteAritcleByIdBo adminDeleteAritcleByIdBos = (AdminDeleteAritcleByIdBo) args[0];

        String token = adminDeleteAritcleByIdBos.getToken();
        adminDeleteAritcleByIdBos.getId();

        return null;
    }

    /**
     * 用户获取自己发布的文章
     * @param joinpoint
     * @return
     * @throws Exception
     */
    @Override
    public String userGetMyselfArticle(JoinPoint joinpoint) throws Exception {
        Object[] args = joinpoint.getArgs();
        UserGetMyselfArticleBo userGetMyselfArticleBo  = (UserGetMyselfArticleBo) args[0];
        String token = userGetMyselfArticleBo.getToken();
        systemVerifyService.getUserIdByToken(token);
        return null;
    }

    /**
     * 创建博文AOP，主要做了防止非幂等操作和token鉴权
     * @param joinpoint
     * @return
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.BlogController.createArticle(..))")
    public void createArticle(JoinPoint joinpoint) throws Exception{
        Object[] args = joinpoint.getArgs();
        CreateArticleBo articleBo = (CreateArticleBo) args[0];

        //1.防止非幂等性操作
        String nonPowerToken = articleBo.getNonPowerToken();
        boolean b = redisTemplate.delete(nonPowerToken);
        if (!b){
            ThreadLocalUtil.mapThreadLocal.get().put("error","异常幂等性操作，请刷新网页重新操作");
            ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.Idempotency);
            return;
        }

        //2.鉴权
        Map<String, Object> map = null;
        String token = articleBo.getToken();
        if (token != null){
            try {
                map = JwtUtil.analysis(token);
                ThreadLocalUtil.mapThreadLocalOfJWT.get().put("userinfo",map);
                //System.err.println("????????");
            } catch (Exception e) {
                //System.out.println("??");
                ThreadLocalUtil.mapThreadLocal.get().put("error","身份验证过期");
                ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.authenticationExpired);
                return ;
            }
        }
    }

    /**
     * 创建题解
     * @param joinpoint
     * @return
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.BlogController.createQuestionAnswer(..))")
    public void createQuestionAnswer(JoinPoint joinpoint) throws Exception{
        Object[] args = joinpoint.getArgs();
        CreateArticleBo articleBo = (CreateArticleBo) args[0];

        //1.防止非幂等性操作
        String nonPowerToken = articleBo.getNonPowerToken();
        boolean b = redisTemplate.delete(nonPowerToken);
        if (!b){
            ThreadLocalUtil.mapThreadLocal.get().put("error","异常幂等性操作，请刷新网页重新操作");
            ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.Idempotency);
            return;
        }

        //2.鉴权
        Map<String, Object> map = null;
        String token = articleBo.getToken();
        if (token != null){
            try {
                map = JwtUtil.analysis(token);
                ThreadLocalUtil.mapThreadLocalOfJWT.get().put("userinfo",map);
                //System.err.println("????????");
            } catch (Exception e) {
                //System.out.println("??");
                ThreadLocalUtil.mapThreadLocal.get().put("error","身份验证过期");
                ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.authenticationExpired);
                return ;
            }
        }
    }

    /**
     * aop切面负责解析token，如果token为null则不做任何处理
     * @param joinpoint
     * @return
     * @throws Exception
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.BlogController.getArticleById(..))")
    public String getArticleById(JoinPoint joinpoint) throws Exception {
        //获取token
        Object[] args = joinpoint.getArgs();
        GetArticleByIdBo articleBo = (GetArticleByIdBo) args[0];
        String token = articleBo.getToken();
        Long articleId = articleBo.getId();
        //如果未登入
        if (token == null || token == ""){
            ThreadLocalUtil.DataOfThreadLocal.get().put("userauth",false);
            return "";
        }
//
//        //鉴权
//        Map<String, Object> map = null;
//        if (token != null){
//            try {
//                map = JwtUtil.analysis(token);
//                ThreadLocalUtil.mapThreadLocalOfJWT.get().put("userinfo",map);
//                //System.err.println("????????");
//            } catch (Exception e) {
//                //System.out.println("??");
//                ThreadLocalUtil.mapThreadLocal.get().put("error","身份验证过期");
//                ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.authenticationExpired);
//                return null;
//            }
//        }
//
//        return null;
        systemVerifyService.isOwnerOrAdmin(token,2,articleId);

        return null;
    }

    /**
     * 管理员分页获取博文
     * @param joinpoint
     * @return
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.BlogController.adminGetArticleByPagin(..))")
    public String adminGetArticleByPagin(JoinPoint joinpoint) throws Exception {
        Object[] args = joinpoint.getArgs();
        AdminGetArticleByPaginBo article = (AdminGetArticleByPaginBo) args[0];
        String token = article.getToken();
        systemVerifyService.veryfiAdmin(token,2);
        return null;
    }

    /**
     * 管理员批量物理删除博文
     * @param joinpoint
     * @return
     * @throws Exception
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.BlogController.physicallyDeleteArticles(..))")
    public String physicallyDeleteArticles(JoinPoint joinpoint) throws Exception {
        Object[] args = joinpoint.getArgs();
        PhysicallyDeleteArticlesBo article = (PhysicallyDeleteArticlesBo) args[0];
        String token = article.getToken();
        systemVerifyService.veryfiAdmin(token,2);
        return null;
    }
}
