package com.dazuizui.business.aop.blog;

import com.dazuizui.business.domain.bo.AddTopArticleBo;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 置顶文档AOP
 */
public interface TopArticleAop {
    /**
     * 增加置顶文章
     * @param joinpoint
     * @return
     */
    public String addTopArticle(JoinPoint joinpoint) throws Exception;


    /**
     * 取消置顶
     * @param joinpoint
     * @throws Exception
     */
    public void untopTheArticle(JoinPoint joinpoint) throws Exception;
}
