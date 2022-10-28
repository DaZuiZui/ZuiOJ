package com.dazuizui.business.util;

import org.omg.CORBA.TIMEOUT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * key
     * @param key
     * @return
     */
    public List  getListInRedis(String key){
        System.out.println("get...");
        return redisTemplate.opsForList().range(key,0,-1);
    }

    /**
     * 添加元素
     * @param key
     * @param timeout
     * @param data
     * @return
     */
    public long  putListInRedis(String key,long timeout,List data){
        for (Object datum : data) {
            System.out.println("add...");
            redisTemplate.opsForList().rightPush(key,datum);
        }
        redisTemplate.expire(key,timeout, TimeUnit.SECONDS);
        return 1;
    }


}
