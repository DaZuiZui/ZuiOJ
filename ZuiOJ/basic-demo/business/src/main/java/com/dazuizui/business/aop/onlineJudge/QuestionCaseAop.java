package com.dazuizui.business.aop.onlineJudge;

import com.dazuizui.basicapi.entry.bo.AddQuestionCaseBo;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 问题案例aop接口
 */
@Component
public interface QuestionCaseAop {
    /**
     * 添加问题案例
     * @param joinpoint
     * @return
     */
    public void addQuestionCase(JoinPoint joinpoint) throws Exception ;
}
