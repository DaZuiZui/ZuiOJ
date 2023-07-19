package com.dazuizui.business.aop.onlineJudge.impl;

import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.business.domain.User;
import com.dazuizui.business.aop.onlineJudge.AcContestQuestionAop;
import com.dazuizui.business.domain.bo.ElementOfQueryLogBo;
import com.dazuizui.business.domain.bo.QueryContestSubmissionLogBo;
import com.dazuizui.business.service.system.SystemVerifyService;
import com.dazuizui.business.service.user.UserService;
import com.dazuizui.business.util.JwtUtil;
import com.dazuizui.business.util.ThreadLocalUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 竞赛提交记录AOp接口实现
 */
@Component
@Aspect
public class AcContestQuestionAopImpl implements AcContestQuestionAop {
    @Autowired
    private UserService userService;
    @Autowired
    private SystemVerifyService systemVerifyService;
    /**
     * 查询竞赛提交记录日记aop接口，主要负责管理员身份的鉴权
     * @param joinpoint
     * @return
     * @throws Exception
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.AcContestQuestionController.queryContestSubmissionLog(..))")
    public String queryContestSubmissionLog(JoinPoint joinpoint) throws Exception {
        Object[] args = joinpoint.getArgs();
        QueryContestSubmissionLogBo queryContestSubmissionLogBo = (QueryContestSubmissionLogBo) args[0];
        String token = queryContestSubmissionLogBo.getToken();

            Map<String, Object> map = null;
            try {
                map = JwtUtil.analysis(token);
                ThreadLocalUtil.mapThreadLocalOfJWT.get().put("userinfo",map);
                //获取登入者id
                String strId = (String) map.get("id");
                Long id = Long.valueOf(strId);
                //查看是否为管理员
                User user = userService.queryUserById(id);


                if (user.getRole() < 2){
                    ThreadLocalUtil.mapThreadLocal.get().put("error","权限不足"+id);
                    ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.insufficientPermissions);
                }
            } catch (Exception e) {
                ThreadLocalUtil.mapThreadLocal.get().put("error","身份验证过期");
                ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.authenticationExpired);
            }

        return null;
    }

    /**
     * 移除当前比赛的所有提交记录
     * @param joinpoint
     * @return
     * @throws Exception
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.AcContestQuestionController.removeAllContestSubmissionLogbyContestId(..))")
    public String removeAllContestSubmissionLogbyContestId(JoinPoint joinpoint) throws Exception {
        //鉴权
        Object[] args = joinpoint.getArgs();
        String token = (String) args[0];
        if (token != null){
            Map<String, Object> map = null;
            try {
                map = JwtUtil.analysis(token);
                ThreadLocalUtil.mapThreadLocalOfJWT.get().put("userinfo",map);
                //获取登入者id
                String strId = (String) map.get("id");
                Long id = Long.valueOf(strId);
                //查看是否为管理员
                User user = userService.queryUserById(id);
                System.err.println(user.getRole() < 2);
                if (user.getRole() < 2){
                    ThreadLocalUtil.mapThreadLocal.get().put("error","权限不足");
                    ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.insufficientPermissions);
                }
            } catch (Exception e) {
                ThreadLocalUtil.mapThreadLocal.get().put("error","身份验证过期");
                ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.authenticationExpired);
            }
        }

        return null;
    }

    /**
     * 通过id删除记录aop切面，主要负责查看是否为管理员权限
     * @param joinpoint
     * @return
     * @throws Exception
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.AcContestQuestionController.deleteLogById(..))")
    public String deleteLogById(JoinPoint joinpoint) throws Exception {
        //鉴权
        Object[] args = joinpoint.getArgs();
        String token = (String) args[0];
        if (token != null){
            Map<String, Object> map = null;
            try {
                map = JwtUtil.analysis(token);
                ThreadLocalUtil.mapThreadLocalOfJWT.get().put("userinfo",map);
                //获取登入者id
                String strId = (String) map.get("id");
                Long id = Long.valueOf(strId);
                //查看是否为管理员
                User user = userService.queryUserById(id);
                //System.err.println(user.getRole() < 2);
                if (user.getRole() < 2){
                    ThreadLocalUtil.mapThreadLocal.get().put("error","权限不足");
                    ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.insufficientPermissions);
                }
            } catch (Exception e) {
                ThreadLocalUtil.mapThreadLocal.get().put("error","身份验证过期");
                ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.authenticationExpired);
            }
        }

        return null;
    }

    /**
     * 监考人员查看提交日志
     * @param joinpoint
     * @return
     * @throws Exception
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.AcContestQuestionController.proctorQueryContestSubmissionLog(..))")
    public String proctorQueryContestSubmissionLog(JoinPoint joinpoint) throws Exception {
        //鉴权
        Object[] args = joinpoint.getArgs();
        QueryContestSubmissionLogBo queryContestSubmissionLogBo = (QueryContestSubmissionLogBo) args[0];
        String token = queryContestSubmissionLogBo.getToken();
        Long contestId = queryContestSubmissionLogBo.getContestId();
        //鉴权
        systemVerifyService.veryfiProctorInContest(token,contestId);
        return null;
    }

    /**
     * 监考人员通过元素筛筛选查询
     * @param joinpoint
     * @return
     * @throws Exception
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.AcContestQuestionController.proctorQueryLogByElement(..))")
    public String proctorQueryLogByElement(JoinPoint joinpoint) throws Exception {
        //鉴权
        Object[] args = joinpoint.getArgs();
        ElementOfQueryLogBo elementOfQueryLogBo = (ElementOfQueryLogBo) args[0];
        String token = elementOfQueryLogBo.getToken();
        Long contestId = elementOfQueryLogBo.getContestId();
        //鉴权
        systemVerifyService.veryfiProctorInContest(token,contestId);
        return null;
    }
}
