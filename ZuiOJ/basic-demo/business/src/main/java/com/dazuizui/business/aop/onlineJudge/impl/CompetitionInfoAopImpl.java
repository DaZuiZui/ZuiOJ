package com.dazuizui.business.aop.onlineJudge.impl;

import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.business.domain.User;
import com.dazuizui.business.aop.onlineJudge.CompetitionInfoAop;
import com.dazuizui.business.domain.bo.AdminAddCompetitionInfoBo;
import com.dazuizui.business.domain.bo.DeleteAllCompetitionInfoByContestIdBo;
import com.dazuizui.business.domain.bo.DeleteTheCompetitionByIdBo;
import com.dazuizui.business.domain.bo.PaglingQueryContestantsInThisContestBo;
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

/**
 * 比赛选手Aop接口shixian
 */
@Aspect
@Component
public class CompetitionInfoAopImpl implements CompetitionInfoAop {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 通过id删除比赛选手aop
     *      主要负责管理员身份的鉴权
     * @param joinpoint
     * @return
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.CompetitionInfoController.deleteTheCompetitionById(..))")
    public String deleteTheCompetitionById(JoinPoint joinpoint) throws Exception {
        Object[] args = joinpoint.getArgs();
        DeleteTheCompetitionByIdBo deleteTheCompetitionByIdBo = (DeleteTheCompetitionByIdBo) args[0];
        String token = deleteTheCompetitionByIdBo.getToken();
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

                if (user.getRole() < 2){
                    ThreadLocalUtil.mapThreadLocal.get().put("error","权限不足");
                    ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.insufficientPermissions);
                }
            } catch (Exception e) {
                ThreadLocalUtil.mapThreadLocal.get().put("error","身份验证过期");
                ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.authenticationExpired);
            }
        }else{
            ThreadLocalUtil.mapThreadLocal.get().put("error","身份验证过期");
            ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.authenticationExpired);
        }

        return null;
    }

    /**
     * 管理员插入比赛选手信息
     *     主要负责管理员权限的鉴别和幂等性
     * @return
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.CompetitionInfoController.adminAddCompetitionInfo(..))")
    public String adminAddCompetitionInfo(JoinPoint joinpoint) throws Exception {
        Object[] args = joinpoint.getArgs();
        AdminAddCompetitionInfoBo adminAddCompetitionInfoBos = (AdminAddCompetitionInfoBo) args[0];


        String nonPowerToken = adminAddCompetitionInfoBos.getNonPowerToken();
        //1.防止非幂等性操作
        if (nonPowerToken == null){
            ThreadLocalUtil.mapThreadLocal.get().put("error","点击过快，请慢一点");
            ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.Idempotency);
            return null;
        }
        boolean b = redisTemplate.delete(nonPowerToken);
        if (!b){
            ThreadLocalUtil.mapThreadLocal.get().put("error","点击过快，请慢一点");
            ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.Idempotency);
            return null;
        }


        String token = adminAddCompetitionInfoBos.getToken();
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
        }else{
            ThreadLocalUtil.mapThreadLocal.get().put("error","身份验证过期");
            ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.authenticationExpired);
        }

        return null;
    }

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

    /**
     * 管理员插入比赛选手信息
     *     主要负责管理员权限的鉴别和幂等性
     * @return
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.CompetitionInfoController.deleteAllCompetitionInfoByContestId(..))")
    public String deleteAllCompetitionInfoByContestId(JoinPoint joinpoint) throws Exception {
        Object[] args = joinpoint.getArgs();
        DeleteAllCompetitionInfoByContestIdBo deleteAllCompetitionInfoByContestIdBos = (DeleteAllCompetitionInfoByContestIdBo) args[0];
        String token = deleteAllCompetitionInfoByContestIdBos.getToken();
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
