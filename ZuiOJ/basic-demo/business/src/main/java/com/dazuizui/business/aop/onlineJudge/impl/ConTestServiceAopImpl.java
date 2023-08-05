package com.dazuizui.business.aop.onlineJudge.impl;

import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.business.domain.User;
import com.dazuizui.business.aop.onlineJudge.ConTestServiceAop;
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
 * @Author 杨易达
 * 哈尔滨广厦学院OJ系统 竞赛aop 代码增强
 */
@Aspect
@Component
public class ConTestServiceAopImpl implements ConTestServiceAop {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserService userService;

    /**
     * 非幂等性问题代码aop增强
     * @param joinpoint
     * @return
     * @throws Exception
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.ConTestController.postContest(..))")
    public void postContest(JoinPoint joinpoint) throws Exception {
        Object[] args = joinpoint.getArgs();
        String nonPowerToken = (String) args[0];
        //1.防止非幂等性操作
        boolean b = redisTemplate.delete(nonPowerToken);
        if (!b){
            ThreadLocalUtil.mapThreadLocal.get().put("error","异常幂等性操作，请刷新网页重新操作");
            ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.Idempotency);
            return ;
        }

        //2.鉴权
        Map<String, Object> map = null;
        String token =  (String) args[1];
        if (token != null){
            try {
                map = JwtUtil.analysis(token);
                ThreadLocalUtil.mapThreadLocalOfJWT.get().put("userinfo",map);
            } catch (Exception e) {
                ThreadLocalUtil.mapThreadLocal.get().put("error","身份验证过期");
                ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.authenticationExpired);
                return ;
            }
        }
        return ;
    }

    /**
     * 幂等性问题代码aop增强
     * @param joinpoint
     * @return
     * @throws Exception
     */

    @Before("execution(* com.dazuizui.business.controller.ConTestController.signUpForTheCompetition(..))")
    public String signUpForTheCompetition(JoinPoint joinpoint) throws Exception {
        //幂等性问题
        Object[] args = joinpoint.getArgs();
        String Idemtoken = (String) args[0];
        boolean b = redisTemplate.delete(Idemtoken);
        if (!b){
            throw new Exception("idempotency");
        }

        //鉴权 todo 优化
        String token = (String) args[1];
        if (token != null){
            Map<String, Object> map = null;
            try {
                map = JwtUtil.analysis(token);
                ThreadLocalUtil.mapThreadLocalOfJWT.get().put("userinfo",map);
            } catch (Exception e) {
                throw new Exception("身份验证过期");

            }
        }

        return null;
    }

    /**
     * 通过id获取赛事aop
     * @param joinpoint
     * @return
     * @throws Exception
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.ConTestController.getEventById(..))")
    public void getEventById(JoinPoint joinpoint) throws Exception {
        //鉴权 todo 优化
        Object[] args = joinpoint.getArgs();
        String token = (String) args[0];
        if (token != null){
            Map<String, Object> map = null;
            try {
                map = JwtUtil.analysis(token);
                ThreadLocalUtil.mapThreadLocalOfJWT.get().put("userinfo",map);
            } catch (Exception e) {
                throw new Exception("身份验证过期");

            }
        }


        return ;
    }


    /**
     * 修改比赛信息
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.ConTestController.updateContest(..))")
    public void updateContest(JoinPoint joinpoint) throws Exception{
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

                if (user.getRole() < 2){
                    ThreadLocalUtil.mapThreadLocal.get().put("error","权限不足");
                    ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.insufficientPermissions);
                }
            } catch (Exception e) {
                ThreadLocalUtil.mapThreadLocal.get().put("error","身份验证过期");
                ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.authenticationExpired);
            }
        }


        return ;
    }
}
