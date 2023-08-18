package com.dazuizui.business.aop.blogrooll;

import org.apache.ibatis.annotations.Param;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


public interface BlogRollAop {
    /**
     * @author
     *
     * @param joinpoint
     */
    public void applyForBlogRoll(JoinPoint joinpoint);

    /**
     * @auhtor Bryan Yang(Dazui)
     * 逻辑删除友情链接
     * Tombstone link
     */
    public void tombstoneLink(JoinPoint joinpoint);

    /**
     * @author Bryan yang(Dazui)
     * 物理删除友情链接
     * Delete BlogRoll
     */
    public void deleteBlogRoll(JoinPoint joinpoint);

 
}
