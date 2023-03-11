package com.dazuizui.business.aop.onlineJudge;

import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Aspect
@Component
public interface QuestionAop {
    /**
     * 根据id获取题目
     *     主要负责鉴权身份是否为管理员
     * @return
     */
    @ApiOperation("根据id获取题目")
    @PostMapping("/adminGetQuestionById")
    public String adminGetQuestionById(JoinPoint joinpoint) throws Exception;
    /**
     * 根据id获取题目
     * @return
     */
    public String getQuestionById(JoinPoint joinpoint) throws Exception;


    /**
     * 提交题目aop 主要负责获取用户信息鉴权
     * @param joinpoint
     * @return
     */
    public String postQuestion(JoinPoint joinpoint) throws Exception;

    /**
     * 用户分页查找数据
     * @param joinpoint
     * @return
     */
    public String pagingToGetQuestionOfAdmin(JoinPoint joinpoint) throws Exception;

    /**
     * 删除问题aop前置环绕鉴权是否权限满足
     * @param joinpoint
     * @return
     * @throws Exception
     */
    public String deleteQuestionById(JoinPoint joinpoint) throws Exception;
}
