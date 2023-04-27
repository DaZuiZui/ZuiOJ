package com.dazuizui.business.aop.onlineJudge;

import com.dazuizui.business.domain.bo.ElementOfQueryLogBo;
import com.dazuizui.business.domain.bo.QueryContestSubmissionLogBo;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.springframework.web.bind.annotation.PostMapping;
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


    /**
     * 移除当前比赛的所有提交记录
     * @param joinpoint
     * @return
     * @throws Exception
     */
    public String removeAllContestSubmissionLogbyContestId(JoinPoint joinpoint) throws Exception;


    /**
     * 通过id删除记录aop切面，主要负责查看是否为管理员权限
     * @param joinpoint
     * @return
     * @throws Exception
     */
    public String deleteLogById(JoinPoint joinpoint) throws Exception;

    /**
     * 查询竞赛提交记录日记aop接口，主要负责管理员身份的鉴权
     * @param joinpoint
     * @return
     * @throws Exception
     */
    public String proctorQueryContestSubmissionLog(JoinPoint joinpoint) throws Exception;


    /**
     * 监考人员通过比赛和名字元素筛选查询元素
     * @return
     */
    public String proctorQueryLogByElement(JoinPoint joinpoint) throws Exception;
}
