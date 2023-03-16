package com.dazuizui.business.util;

import com.dazuizui.basicapi.entry.RedisKey;
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
        boolean b = redisTemplate.delete(key);
        return -1;
    }



    /**
     * 自增
     * @param key
     * @return
     */
    public long increment(String key,long timeout,long val){
        //System.err.println("before "+redisTemplate.opsForValue().get(key));
        Long increment = stringRedisTemplate.opsForValue().increment(key,val);
        stringRedisTemplate.expire(key,timeout,TimeUnit.SECONDS);
        //System.err.println(increment+"before "+redisTemplate.opsForValue().get(key));
        return increment;
    }

    /**
     * 设置string类型
     * @param key
     * @param timeout
     * @param data
     * @return
     */
    public long setLongOfStringInRedis(String key,long timeout,Long data){
        System.err.println("key is"+key+" out time"+timeout);
        stringRedisTemplate.opsForValue().increment(key, data);
        stringRedisTemplate.expire(key,timeout,TimeUnit.SECONDS);
        return -1;
    }

    /**
     * 获取Long in string类型
     * @param key
     * @return
     */
    public Long getLongOfStringInRedis(String key){
        String obj = stringRedisTemplate.opsForValue().get(key);
        Long expire = stringRedisTemplate.getExpire(key);
        System.err.println(expire);
        /**
         * 如果没有获取到值，或者过期时间少于2秒则不做操作
         */
        if (obj == null || expire < 2){
            return null;
        }
        //更新过期时间
        stringRedisTemplate.expire(key, RedisKey.OutTime,TimeUnit.SECONDS);

        return Long.valueOf(obj);
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
            redisTemplate.opsForList().rightPush(key,datum);
        }
        redisTemplate.expire(key,timeout, TimeUnit.SECONDS);
        return 1;
    }


}
