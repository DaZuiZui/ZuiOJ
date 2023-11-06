package com.dazuizui.business.aop.blog.impl;

import com.dazuizui.business.aop.blog.TopArticleAop;
import com.dazuizui.business.domain.TopArticle;
import com.dazuizui.business.domain.bo.AddTopArticleBo;
import com.dazuizui.business.domain.bo.UntopTheArticleBo;
import com.dazuizui.business.service.system.SystemVerifyService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 置顶文章Aop实现类
 */
@Component
@Aspect
public class TopArticleAopImpl implements TopArticleAop {
    @Autowired
    private SystemVerifyService systemVerifyService;

    /**
     * 添加文章
     * @param joinpoint
     * @return
     * @throws Exception
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.TopArticleController.addTopArticle(..))")
    public String addTopArticle(JoinPoint joinpoint) throws Exception {
        Object[] args = joinpoint.getArgs();
        AddTopArticleBo addTopArticleBos = (AddTopArticleBo) args[0];
        String token = addTopArticleBos.getToken();
        String nonPowerToken = addTopArticleBos.getNonPowerToken();
        //鉴权
        systemVerifyService.verfiNonPowerTokenAndAdminToken(nonPowerToken,token,2);
        return null;
    }


    /**
     * 取消置顶
     * @param joinpoint
     * @throws Exception
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.TopArticleController.untopTheArticle(..))")
    public void untopTheArticle(JoinPoint joinpoint) throws Exception {
        Object[] args = joinpoint.getArgs();
        UntopTheArticleBo untopTheArticleBo = (UntopTheArticleBo) args[0];
        systemVerifyService.veryfiAdmin(untopTheArticleBo.getToken(), 2);
    }
}
