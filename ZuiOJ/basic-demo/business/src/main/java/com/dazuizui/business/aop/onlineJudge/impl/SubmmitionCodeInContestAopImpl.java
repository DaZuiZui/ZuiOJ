package com.dazuizui.business.aop.onlineJudge.impl;

import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.business.domain.User;
import com.dazuizui.business.aop.onlineJudge.SubmmitionCodeInContestAop;
import com.dazuizui.business.domain.bo.DuplicateCodeBo;
import com.dazuizui.business.domain.bo.FilterQueryMatchSaveCodeBo;
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

/**
 * 比赛时候提交保存的代码Aop实现
 */
@Component
@Aspect
public class SubmmitionCodeInContestAopImpl implements SubmmitionCodeInContestAop {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SystemVerifyService systemVerifyService;

    /**
     * @author Bryan Yang(Dazui) 30 8/2023
     *
     * 获取涉嫌重复的代码
     *    主要做了鉴权是否为管理员
     * @param joinpoint
     * @return
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.SubmmitionCodeInContestController.getDuplicateCode(..))")
    public void getDuplicateCode(JoinPoint joinpoint){
        Object[] args = joinpoint.getArgs();
        DuplicateCodeBo duplicateCodeBo = (DuplicateCodeBo) args[0];
        String token = duplicateCodeBo.getToken();

        systemVerifyService.veryfiAdmin(token,2);
    }

    /**
     * 筛选查询数据，主要做了身份验证，查看是否符合权限查看
     * @param joinpoint
     * @return
     * @throws Exception
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.SubmmitionCodeInContestController.filterQueryMatchSaveCode(..))")
    public String filterQueryMatchSaveCode(JoinPoint joinpoint) throws Exception {
        Object[] args = joinpoint.getArgs();
        FilterQueryMatchSaveCodeBo filterQueryMatchSaveCodeBo = (FilterQueryMatchSaveCodeBo) args[0];
        String token = filterQueryMatchSaveCodeBo.getToken();
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
     * 通过id查询代码详细信息
     *      筛选查询数据，主要做了身份验证，查看是否符合权限查看
     * @param joinpoint
     * @return
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.SubmmitionCodeInContestController.findOneById(..))")
    public String findOneById(JoinPoint joinpoint) throws Exception {
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
        }else{
            ThreadLocalUtil.mapThreadLocal.get().put("error","身份验证过期");
            ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.authenticationExpired);
        }

        return null;
    }


    /**
     * 通过提交记录id删除记录
     * @param joinpoint
     * @return
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.SubmmitionCodeInContestController.deleteById(..))")
    public String deleteById(JoinPoint joinpoint) throws Exception {
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
        }else{
            ThreadLocalUtil.mapThreadLocal.get().put("error","身份验证过期");
            ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.authenticationExpired);
        }

        return null;
    }
}
