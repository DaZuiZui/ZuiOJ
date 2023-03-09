package com.dazuizui.business.aop.onlineJudge.impl;

import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.User;
import com.dazuizui.business.aop.onlineJudge.CompetitionInfoAop;
import com.dazuizui.business.domain.bo.PaglingQueryContestantsInThisContestBo;
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
 * 比赛选手Aop接口shixian
 */
@Aspect
@Component
public class CompetitionInfoAopImpl implements CompetitionInfoAop {
    @Autowired
    private UserService userService;
    /**
     * 分页获取比赛选手数据
     * @param joinpoint
     * @return
     * @throws Exception
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.CompetitionInfoController.paglingQueryContestantsInThisContest(..))")
    public String paglingQueryContestantsInThisContest(JoinPoint joinpoint) throws Exception {
        Object[] args = joinpoint.getArgs();
        PaglingQueryContestantsInThisContestBo paglingQueryContestantsInThisContestBo = (PaglingQueryContestantsInThisContestBo) args[0];
        String token = paglingQueryContestantsInThisContestBo.getToken();
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
}
