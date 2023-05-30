package com.dazuizui.business.aop.onlineJudge.impl;

import com.dazuizui.basicapi.entry.CompetitionInfo;
import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.User;
import com.dazuizui.basicapi.entry.bo.DeleteQuestion;
import com.dazuizui.basicapi.entry.bo.QuestionBankBo;
import com.dazuizui.business.aop.onlineJudge.QuestionAop;
import com.dazuizui.business.domain.bo.BatchPhysicalDeleteQuestionsBo;
import com.dazuizui.business.domain.bo.UpdateQuestionAndLimitByQuestionIdBo;
import com.dazuizui.business.mapper.CompetitionInfoMapper;
import com.dazuizui.business.service.onlineJudge.SystemService;
import com.dazuizui.business.service.system.SystemVerifyService;
import com.dazuizui.business.service.user.UserService;
import com.dazuizui.business.util.JwtUtil;
import com.dazuizui.business.util.ThreadLocalUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Aspect
@Component
public class QuestionAopImpl implements QuestionAop {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private CompetitionInfoMapper competitionInfoMapper;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Autowired
    private UserService userService;
    @Autowired
    private SystemVerifyService systemVerifyService;

    /**
     * 修改题目和题目limit
     *     主要负责鉴权身份是否为管理员
     * @return
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.QuestionBankController.updateQuestionAndLimitByQuestionId(..))")
    public String updateQuestionAndLimitByQuestionId(JoinPoint joinpoint) throws Exception {

        Object[] args = joinpoint.getArgs();
        UpdateQuestionAndLimitByQuestionIdBo updateQuestionAndLimitByQuestionIdBo = (UpdateQuestionAndLimitByQuestionIdBo) args[0];
        String token = updateQuestionAndLimitByQuestionIdBo.getToken();
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
        return null;
    }

    /**
     * 根据id获取题目
     *     主要负责鉴权身份是否为管理员
     * @return
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.QuestionBankController.adminGetQuestionById(..))")
    public String adminGetQuestionById(JoinPoint joinpoint) throws Exception{
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


        return null;
    }

    /**
     * 通过题目id获取题目的AOP
     * @param joinpoint
     * @return
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.QuestionBankController.getQuestionById(..))")
    public String getQuestionById(JoinPoint joinpoint) throws Exception {
        //查看身份验证是否过期
        Object[] args = joinpoint.getArgs();
        String token = (String) args[0];
        if (token != null){
            Map<String, Object> map = null;
            try {
                map = JwtUtil.analysis(token);
                ThreadLocalUtil.mapThreadLocalOfJWT.get().put("userinfo",map);
                //System.err.println("threadlocal is "+ThreadLocalUtil.mapThreadLocalOfJWT.get().get("userinfo"));
            } catch (Exception e) {
                throw new Exception("身份验证过期");
            }
        }

        //查看是否获取的题目是比赛类型
        Integer questionType = (Integer) args[2];
        if (questionType != 1){
            //查看是否为比赛选手
            String JWTStringID = (String) ThreadLocalUtil.mapThreadLocalOfJWT.get().get("userinfo").get("id");
            Long id = Long.valueOf(JWTStringID);
            CompetitionInfo competitionInfo = new CompetitionInfo();
            competitionInfo.setUserId(id);
            competitionInfo.setContestId((Long) args[3]);
            CompetitionInfo competitionInfoInDB = competitionInfoMapper.checkForEntry(competitionInfo);
            //用户无比赛权限
            if (competitionInfoInDB == null) {
                ThreadLocalUtil.mapThreadLocal.get().put("error","0x8769");
                return null;
            }
        }
        return null;
    }

    /**
     * 提交题目aop 主要做了身份验证还有幂等性处理
     * @param joinpoint
     * @return
     * @throws Exception
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.QuestionBankController.postQuestion(..))")
    public String postQuestion(JoinPoint joinpoint) throws Exception {
        //幂等性问题
        Object[] args = joinpoint.getArgs();
        String Idemtoken = (String) args[0];
        boolean b = redisTemplate.delete(Idemtoken);
        if (!b){
            ThreadLocalUtil.mapThreadLocal.get().put("error","异常幂等性操作，请刷新网页重新操作");
            ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.Idempotency);
            return "";
        }

        //鉴权
        String token = (String) args[1];
        if (token != null){
            Map<String, Object> map = null;
            try {
                map = JwtUtil.analysis(token);
                ThreadLocalUtil.mapThreadLocalOfJWT.get().put("userinfo",map);
            } catch (Exception e) {
                ThreadLocalUtil.mapThreadLocal.get().put("error","身份验证过期");
                ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.authenticationExpired);
                return "";
            }
        }

        return null;
    }

    /**
     * 管理员分页查询题目，鉴权是否有权限
     * @param joinpoint
     * @return
     * @throws Exception
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.QuestionBankController.pagingToGetQuestionOfAdmin(..))")
    public String pagingToGetQuestionOfAdmin(JoinPoint joinpoint) throws Exception {
        //鉴权
        Object[] args = joinpoint.getArgs();
        String token = (String) args[0];
        Map<String, Object> map = null;

        if (token != null){
            try {
                map = JwtUtil.analysis(token);
                ThreadLocalUtil.mapThreadLocalOfJWT.get().put("userinfo",map);
            } catch (Exception e) {
                ThreadLocalUtil.mapThreadLocal.get().put("error","身份验证过期");
                ThreadLocalUtil.mapThreadLocal.get().put("code",StatusCode.authenticationExpired);
            }
        }

        Long id = Long.valueOf((String) map.get("id"));
        User user = userService.queryUserById(id);
        Integer role = user.getRole();
        if (role < 2){
            ThreadLocalUtil.mapThreadLocal.get().put("error","权限不足");
            ThreadLocalUtil.mapThreadLocal.get().put("code",StatusCode.insufficientPermissions);
            return null;
        }

        return null;
    }

    /**
     * 删除问题aop前置环绕鉴权是否权限满足
     * @param joinpoint
     * @return
     * @throws Exception
     */
    @Override
    @Before("execution(* com.dazuizui.business.controller.QuestionBankController.deleteQuestionById(..))")
    public String deleteQuestionById(JoinPoint joinpoint) throws Exception{
        //鉴权
        Object[] args = joinpoint.getArgs();
        DeleteQuestion arg = (DeleteQuestion) args[0];
        Map<String, Object> map = null;
        String token = arg.getToken();
        System.err.println("toekn si "+token);
        if (token != null){
            try {
                map = JwtUtil.analysis(token);
                ThreadLocalUtil.mapThreadLocalOfJWT.get().put("userinfo",map);
            } catch (Exception e) {

                ThreadLocalUtil.mapThreadLocal.get().put("error","身份验证过期");
                ThreadLocalUtil.mapThreadLocal.get().put("code",StatusCode.authenticationExpired);
                return null;
            }
        }
        System.err.println("??"+map);
        Long id = Long.valueOf((String) map.get("id"));
        User user = userService.queryUserById(id);
        Integer role = user.getRole();
        if (role < 2){
            ThreadLocalUtil.mapThreadLocal.get().put("error","权限不足");
            ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.insufficientPermissions);
            return null;
        }

        return null;
    }

    /**
     * 批量物理删除quesiton  主要查看是否拥有管理员权限
     * @param joinpoint
     * @throws Exception
     */
    @Override
    public void batchDeleteQuestions(JoinPoint joinpoint) throws Exception {

        Object[] args = joinpoint.getArgs();
        BatchPhysicalDeleteQuestionsBo batchPhysicalDeleteQuestionsBo = (BatchPhysicalDeleteQuestionsBo) args[0];
        String token  = batchPhysicalDeleteQuestionsBo.getToken();
        systemVerifyService.veryfiAdmin(token,2);

    }

}
