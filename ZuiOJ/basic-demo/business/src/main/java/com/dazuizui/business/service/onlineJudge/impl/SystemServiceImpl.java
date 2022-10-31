package com.dazuizui.business.service.onlineJudge.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.service.onlineJudge.SystemService;
import com.dazuizui.business.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 系统接口实现类
 */
@Service
public class SystemServiceImpl implements SystemService {
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String getNonPower() {
        String uuid = UUID.randomUUID().toString();
        redisUtil.setStringInRedis(uuid,60*5,1);
        return JSONArray.toJSONString(new ResponseVo<>("幂等性token",uuid,"0x99999"));
    }
}
