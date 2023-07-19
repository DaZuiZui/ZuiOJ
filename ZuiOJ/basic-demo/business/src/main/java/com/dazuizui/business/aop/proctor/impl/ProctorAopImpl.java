package com.dazuizui.business.aop.proctor.impl;

import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.business.domain.User;
import com.dazuizui.business.aop.proctor.ProctorAop;
import com.dazuizui.business.domain.bo.*;
import com.dazuizui.business.service.proctor.ProctorService;
import com.dazuizui.business.service.system.SystemVerifyService;
import com.dazuizui.business.service.user.UserService;
import com.dazuizui.business.util.JwtUtil;
import com.dazuizui.business.util.ThreadLocalUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
@Aspect
public class ProctorAopImpl implements ProctorAop {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ProctorService proctorService;
    @Autowired
    private SystemVerifyService systemVerifyService;



    /**
     * 添加一个面试官Aop 前置切面，主要负责了鉴别是否为监考人员
     * @param joinpoint
     * @return
     */
    @Before("execution(* com.dazuizui.business.controller.ProctorController.addProctor(..))")
    public void addProctor(JoinPoint joinpoint) throws Exception{
        Object[] args = joinpoint.getArgs();
        AddProctorBo addProctorBo = (AddProctorBo) args[0];
        String token = addProctorBo.getToken();

        String nonPowerToken =  addProctorBo.getNonPowerToken();

        //1.防止非幂等性操作
        boolean b = redisTemplate.delete(nonPowerToken);
        if (!b){
            ThreadLocalUtil.mapThreadLocal.get().put("error","异常幂等性操作，请刷新网页重新操作");
            ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.Idempotency);
            return ;
        }
        //身份矫正
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
                ThreadLocalUtil.mapThreadLocal.get().put("error","权限不足");
                ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.insufficientPermissions);
            }

        } catch (Exception e) {
            ThreadLocalUtil.mapThreadLocal.get().put("error","身份验证过期");
            ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.authenticationExpired);
        }
    }


    /**
     * 监考人员获取未来进行时候和现在进行时的差事
     * @param joinpoint
     * @throws Exception
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.ProctorController.proctorGetFutureEventsInfo(..))")
    public void proctorGetFutureEventsInfo(JoinPoint joinpoint) throws Exception {
        Object[] args = joinpoint.getArgs();
        ProctorGetFutureEventsInfoBo proctorGetFutureEventsInfoBo = (ProctorGetFutureEventsInfoBo) args[0];
        String token = proctorGetFutureEventsInfoBo.getToken();
        //鉴权管理员身份
        systemVerifyService.veryfiProctor(token);
    }

    /**
     * 监考人员获取已经获取时的AOP处理
     * @param joinpoint
     * @throws Exception
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.ProctorController.proctorGetLastEventsInfo(..))")
    public void proctorGetLastEventsInfo(JoinPoint joinpoint) throws Exception {
        Object[] args = joinpoint.getArgs();
        ProctorGetFutureEventsInfoBo proctorGetFutureEventsInfoBo = (ProctorGetFutureEventsInfoBo) args[0];
        String token = proctorGetFutureEventsInfoBo.getToken();
        //鉴权管理员身份
        systemVerifyService.veryfiProctor(token);
    }

    /**
     * 监考人员获取
     * @param joinpoint
     * @return
     * @throws Exception
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.ProctorController.paglingQueryContestantsInThisContest(..))")
    public void paglingQueryContestantsInThisContest(JoinPoint joinpoint) throws Exception {
        Object[] args = joinpoint.getArgs();
        PaglingQueryContestantsInThisContestBo paglingQueryContestantsInThisContestBo = (PaglingQueryContestantsInThisContestBo) args[0];
        Long contestId = paglingQueryContestantsInThisContestBo.getContestId();
        String token = paglingQueryContestantsInThisContestBo.getToken();
        systemVerifyService.veryfiProctorInContest(token,contestId);
        return ;
    }

    /**
     * 筛选查询比赛提交保存的代码 主要做了监考身份的鉴权
     * @param joinpoint
     * @return
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.ProctorController.proctorFilterQueryMatchSaveCode(..))")
    public String proctorFilterQueryMatchSaveCode(JoinPoint joinpoint) throws Exception {
        Object[] args = joinpoint.getArgs();
        FilterQueryMatchSaveCodeBo filterQueryMatchSaveCodeBos = (FilterQueryMatchSaveCodeBo) args[0];
        String token = filterQueryMatchSaveCodeBos.getToken();
        Long contestId = filterQueryMatchSaveCodeBos.getContestId();
        systemVerifyService.veryfiProctorInContest(token,contestId);
        return null;
    }

    /**
     * 通过问题获取id
     * @param joinpoint
     * @return
     * @throws Exception
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.ProctorController.getQuestionById(..))")
    public String getQuestionById(JoinPoint joinpoint) throws Exception {
        Object[] args = joinpoint.getArgs();
        String token = (String) args[0];
        Long contestId = (Long) args[2];
        systemVerifyService.veryfiProctorInContest(token,contestId);
        return null;
    }

    /**
     * 管理员分页获取监考人员数据
     * @param
     * @return
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.ProctorController.adminGetProctorsByPagin(..))")
    public String adminGetProctorsByPagin(JoinPoint joinpoint) throws Exception {
        Object[] args = joinpoint.getArgs();
        AdminGetProctorsByPaginBo adminGetProctorsByPaginBos = (AdminGetProctorsByPaginBo) args[0];
        String token = adminGetProctorsByPaginBos.getToken();
        //鉴权
        systemVerifyService.veryfiAdmin(token,2);
        return null;
    }

    /**
     * 管理员删除监考人员通过监考人员Id
     * @param
     * @return
     */
    @Override
    public String adminDeleteProctorByIdOfProctor(JoinPoint joinpoint) throws Exception {
        Object[] args = joinpoint.getArgs();
        AdminDeleteProctorByIdBo adminDeleteProctorByIdBos = (AdminDeleteProctorByIdBo) args[0];
        String token = adminDeleteProctorByIdBos.getToken();
        //鉴权
        systemVerifyService.veryfiAdmin(token,2);
        return null;
    }


    /**
     * 监考人员通过id查询代码详细信息
     *     主要负责检查监考人员是否为该比赛的监考人员
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.ProctorController.findOneCodeDetailedById(..))")
    public String findOneCodeDetailedById(JoinPoint joinpoint) throws Exception {
        Object[] args = joinpoint.getArgs();
        //获取参数
        String token = (String) args[0];
        Long contestId = (Long) args[2];
        systemVerifyService.veryfiProctorInContest(token,contestId);

        return null;
    }
}
