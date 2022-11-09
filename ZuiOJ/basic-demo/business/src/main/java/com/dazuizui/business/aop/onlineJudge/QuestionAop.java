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
     * @return
     */
    public String getQuestionById(JoinPoint joinpoint);


    /**
     * 提交题目aop 主要负责获取用户信息鉴权
     * @param joinpoint
     * @return
     */
    public String postQuestion(JoinPoint joinpoint) throws Exception;
}
