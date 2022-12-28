package com.dazuizui.business.util;

import org.omg.CORBA.TIMEOUT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 批量删除
     * @param key
     * @return
     */
    public long batchDeletion(List<String> key){
        //我
        redisTemplate.delete(key);
        return -1;
    }

    //批量获取
    public List<Object> batchGetDateOfStringType(List<String> list){
        List<Object> res = (List<Object>) redisTemplate.opsForValue().multiGet(list);
        return res;
    }

    /**
     * 删除key
     * @param key
     * @return
     */
    public long deleteKey(String key){
        redisTemplate.delete(key);
        return -1;
    }



    /**
     * 自增
     * @param key
     * @return
     */
    public long increment(String key){
        System.err.println("before "+redisTemplate.opsForValue().get(key));
        Long increment = stringRedisTemplate.opsForValue().increment(key);
        System.err.println(increment+"before "+redisTemplate.opsForValue().get(key));
        return increment;
    }

    /**
     * 设置string类型
     * @param key
     * @param timeout
     * @param data
     * @return
     */
    public long setLongOfStringInRedis(String key,long timeout,String data){
        stringRedisTemplate.opsForValue().set(key, data);
        stringRedisTemplate.expire(key,timeout,TimeUnit.SECONDS);
        return -1;
    }

    /**
     * 设置key String类型
     * @param key
     * @param timeout
     * @param data
     * @return
     */
    public long  putListInRedis(String key,long timeout,Object data){
        redisTemplate.opsForValue().set(key,data);
        redisTemplate.expire(key,timeout,TimeUnit.SECONDS);
        return 1;
    }



    /**
     * 获取key值
     * @param key
     * @return
     */
    public Object getStringInRedis(String key){
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置string类型
     * @param key
     * @param timeout
     * @param data
     * @return
     */
    public long setStringInRedis(String key,long timeout,Object data){
        redisTemplate.opsForValue().set(key,data);
        redisTemplate.expire(key,timeout,TimeUnit.SECONDS);
        return -1;
    }


    /**
     * get list
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
