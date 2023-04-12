package com.dazuizui.business.service.proctor.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.RedisKey;
import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.StatusCodeMessage;
import com.dazuizui.basicapi.entry.User;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.Proctor;
import com.dazuizui.business.domain.bo.AddProctorBo;
import com.dazuizui.business.mapper.ProctorAttributeMapper;
import com.dazuizui.business.mapper.ProctorMapper;
import com.dazuizui.business.service.proctor.ProctorService;
import com.dazuizui.business.service.user.UserService;
import com.dazuizui.business.util.RedisUtil;
import com.dazuizui.business.util.ThreadLocalUtil;
import com.dazuizui.business.util.TransactionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 *  面试官业务实现层
 */
@Service
public class ProctorServiceImpl implements ProctorService {
    @Autowired
    private TransactionUtils transactionUtils;
    @Autowired
    private ProctorMapper proctorMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private ProctorAttributeMapper proctorAttributeMapper;
    /**
     * 添加一个面试官
     * @param addProctorBo
     * @return
     */
    @Override
    public String addProctor(AddProctorBo addProctorBo){
        //查询该用户的主键
        User user = userService.queryUserByUsername(addProctorBo.getUsername());

        //用户名不存在
        if (user == null){
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.ThisUsernameDoesNotExist,null, StatusCode.ThisUsernameDoesNotExist));
        }

        //封装
        Proctor proctor  =new Proctor();
        proctor.setContestId(addProctorBo.getContestId());
        proctor.setUserId(user.getId());
        proctor.setCreateBy(Long.valueOf(ThreadLocalUtil.mapThreadLocalOfJWT.get().get("userinfo").get("id")+""));
        proctor.setCreateTime(new Date());

        //添加到mysql
        Long aLong = proctorMapper.addProctor(proctor);
        if (aLong == 0){
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
        }
        //增加监考数量
        aLong = proctorAttributeMapper.increaseTheNumberOfProctors(addProctorBo.getContestId(),1l);
        if (aLong == 0){
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
        }

        //添加Redis
        redisUtil.setStringInRedis(RedisKey.ZuiBlogInvigilatorUserId,RedisKey.OutTime,proctor);

        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,null, StatusCode.OK));
    }
}
