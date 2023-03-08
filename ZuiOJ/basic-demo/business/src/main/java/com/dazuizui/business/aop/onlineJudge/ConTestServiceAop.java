package com.dazuizui.business.aop.onlineJudge;

import com.dazuizui.basicapi.entry.Contest;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Component
public interface ConTestServiceAop {
    /**
     * 修改比赛信息
     */
    public void updateContest(JoinPoint joinpoint) throws Exception;
    /**
     * 提交竞赛
     * @param joinpoint
     * @return
     * @throws Exception
     */
    public String postContest(JoinPoint joinpoint) throws Exception;

    /**
     * 报名aop
     * @param joinpoint
     * @return
     * @throws Exception
     */
    public String signUpForTheCompetition(JoinPoint joinpoint) throws Exception;

    /**
     * 通过id获取赛事
     * @param joinpoint
     * @return
     * @throws Exception
     */
    public String getEventById(JoinPoint joinpoint) throws Exception;


}
