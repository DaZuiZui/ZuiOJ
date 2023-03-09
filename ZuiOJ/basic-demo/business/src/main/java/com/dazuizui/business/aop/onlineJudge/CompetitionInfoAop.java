package com.dazuizui.business.aop.onlineJudge;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

/**
 * 比赛选手Aop接口
 */
@Component
public interface CompetitionInfoAop {
    /**
     * 分页获取比赛选手数据
     * 鉴权查看是否为管理员
     * @return
     */
    public String paglingQueryContestantsInThisContest(JoinPoint joinpoint) throws Exception;
}
