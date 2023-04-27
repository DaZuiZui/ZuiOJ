package com.dazuizui.business.service.system.impl;

import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.business.domain.Proctor;
import com.dazuizui.business.service.proctor.ProctorService;
import com.dazuizui.business.service.system.SystemVerifyService;
import com.dazuizui.business.util.JwtUtil;
import com.dazuizui.business.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/***
 * 系统验证接口实现类
 */
@Service
public class SystemVerifyServiceImpl implements SystemVerifyService {
    @Autowired
    private ProctorService proctorService;

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
}
