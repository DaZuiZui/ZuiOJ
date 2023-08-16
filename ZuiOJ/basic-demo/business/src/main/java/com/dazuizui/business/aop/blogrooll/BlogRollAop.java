package com.dazuizui.business.aop.blogrooll;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


public interface BlogRollAop {
    public void applyForBlogRoll(JoinPoint joinpoint);


 
}
