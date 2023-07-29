package com.dazuizui.business.service.system.impl;

import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.business.domain.User;
import com.dazuizui.business.domain.Proctor;
import com.dazuizui.business.service.blog.BlogService;
import com.dazuizui.business.service.proctor.ProctorService;
import com.dazuizui.business.service.system.SystemVerifyService;
import com.dazuizui.business.service.user.UserService;
import com.dazuizui.business.util.JwtUtil;
import com.dazuizui.business.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/***
 * 系统验证接口实现类
 */
@Service
public class SystemVerifyServiceImpl implements SystemVerifyService {
    @Autowired
    private ProctorService proctorService;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private BlogService blogService;
    /**
     * 验证是否为监考人员
     * @param token
     * @return
     */
    @Override
    public boolean veryfiProctor(String token) {
        if (token == null || token == ""){
            ThreadLocalUtil.mapThreadLocal.get().put("error","权限不足");
            ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.insufficientPermissions);
            return false;
        }
        //身份校验
        Map<String, Object> map = null;
        map = JwtUtil.analysis(token);
        ThreadLocalUtil.mapThreadLocalOfJWT.get().put("userinfo",map);
        //获取登入者id
        String strId = (String) map.get("id");
        Long id = Long.valueOf(strId);
        //查看是否为有监考历史
        Proctor proctor = proctorService.findByIdLimit1(id);
        if (proctor == null){
            ThreadLocalUtil.mapThreadLocal.get().put("error","权限不足");
            ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.insufficientPermissions);
        }
        return true;
    }

    /**
     * 验证此用户是否为在指定比赛的考官
     * @param token
     * @return
     */
    @Override
    public boolean veryfiProctorInContest(String token,Long contestId) {
        //身份鉴权
        boolean b = this.veryfiProctor(token);
        if (b == false){
            return false;
        }
        Long userId = Long.valueOf((String) ThreadLocalUtil.mapThreadLocalOfJWT.get().get("userinfo").get("id"));
        //查看是否为当前比赛考员
        Proctor byContestIdAndUserId = proctorService.findByContestIdAndUserId(contestId, userId);
        if (byContestIdAndUserId == null){
            ThreadLocalUtil.mapThreadLocal.get().put("error","权限不足");
            ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.insufficientPermissions);
            return false;
        }
        return true;
    }

    /**
     * 验证是否为管理员
     * 如果是管理员就返回true，否则就返回false
     * @param token 用户token
     * @param leave 权限等级
     * @return
     */
    @Override
    public boolean veryfiAdmin(String token,Integer leave) {
        //非空判断
        if (token == null || token == ""){
            ThreadLocalUtil.mapThreadLocal.get().put("error","权限不足");
            ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.insufficientPermissions);
            return false;
        }
        //身份校验
        Map<String, Object> map = null;
        map = JwtUtil.analysis(token);
        ThreadLocalUtil.mapThreadLocalOfJWT.get().put("userinfo",map);
        //获取登入者id
        String strId = (String) map.get("id");
        Long id = Long.valueOf(strId);
        //查看是否为管理员
        User user = userService.queryUserById(id);
        if (user.getRole() < leave){
            ThreadLocalUtil.mapThreadLocal.get().put("error","权限不足");
            ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.insufficientPermissions);
            return false;
        }

        return true;
    }

    /**
     * 解决接口幂等性的问题
     * @param nonPowerToken
     * @return
     */
    @Override
    public boolean verfiNonPowerToken(String nonPowerToken){
        boolean b = redisTemplate.delete(nonPowerToken);
        if (!b){
            ThreadLocalUtil.mapThreadLocal.get().put("error","异常幂等性操作，请刷新网页重新操作");
            ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.Idempotency);
            return false;
        }
        return true;
    }

    /**
     * 鉴权用户权限和接口幂等性问题
     * @param nonPowerToken
     * @param token
     * @return
     */
    @Override
    public boolean verfiNonPowerTokenAndAdminToken(String nonPowerToken,String token,Integer leave){
        boolean b = this.verfiNonPowerToken(nonPowerToken);
        if (b == false){
            return false;
        }

        b = this.veryfiAdmin(token,leave);
        if ( b == false){
            return false;
        }

        return true;
    }

    /**
     * 查看是否为自己的的文章
     * @param token
     * @param id
     * @return
     */
    @Override
    public boolean isMyArticle(String token, Long id) {
        //非空判断
        if (token == null || token == ""){
            ThreadLocalUtil.mapThreadLocal.get().put("error","权限不足");
            ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.insufficientPermissions);
            return false;
        }
        //身份校验
        Map<String, Object> map = null;
        map = JwtUtil.analysis(token);
        ThreadLocalUtil.mapThreadLocalOfJWT.get().put("userinfo",map);
        //获取登入者id
        String strId = (String) map.get("id");
        Long userId = Long.valueOf(strId);
        //获取该博文的发布者
        Long userIdByArticleId = blogService.getUserIdByArticleId(id);
        if (userIdByArticleId == userId){
            return true;
        }

        ThreadLocalUtil.mapThreadLocal.get().put("error","权限不足");
        ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.insufficientPermissions);
        return false;
    }

    /**
     * 鉴权是否为管理员或者该内容的主人
     * @param token
     * @param leave
     * @return
     */
    @Override
    public boolean isOwnerOrAdmin(String token, Integer leave, Long id) {
        boolean myArticle = this.isMyArticle(token, id);
        if (myArticle == false){
            ThreadLocalUtil.mapThreadLocalOfJWT.remove();
            boolean isAdmin = this.veryfiAdmin(token, leave);
            return isAdmin;
        }

        return true;
    }

    /**
     * 通过token获取用户id
     * @param token
     * @return
     */
    @Override
    public boolean getUserIdByToken(String token){
        //非空判断
        if (token == null || token == ""){
            ThreadLocalUtil.mapThreadLocal.get().put("error","权限不足");
            ThreadLocalUtil.mapThreadLocal.get().put("code", StatusCode.insufficientPermissions);
            return false;
        }
        //身份校验
        Map<String, Object> map = null;
        map = JwtUtil.analysis(token);
        ThreadLocalUtil.mapThreadLocalOfJWT.get().put("userinfo",map);

        return true;
    }
}
