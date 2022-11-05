package com.dazuizui.business.aop.onlineJudge;

import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Aspect
@Component
public interface QuestionAop {
    /**
     * 根据id获取题目
     * @param token
     * @param id
     * @return
     */
    public String getQuestionById(JoinPoint joinpoint);
}
