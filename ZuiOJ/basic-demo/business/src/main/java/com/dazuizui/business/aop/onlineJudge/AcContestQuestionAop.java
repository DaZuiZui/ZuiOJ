package com.dazuizui.business.aop.onlineJudge;

import com.dazuizui.business.domain.bo.QueryContestSubmissionLogBo;
import org.aspectj.lang.JoinPoint;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 竞赛提交记录AOp接口
 */
public interface AcContestQuestionAop {
    /**
     * 查询竞赛提交记录日记aop接口，主要负责管理员身份的鉴权
     * @param joinpoint
     * @return
     * @throws Exception
     */
    public String queryContestSubmissionLog(JoinPoint joinpoint) throws Exception;
}
