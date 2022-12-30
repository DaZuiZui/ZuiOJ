package com.dazuizui.business;

import com.baomidou.mybatisplus.extension.api.R;
import com.dazuizui.basicapi.entry.Contest;
import com.dazuizui.basicapi.entry.QuestionCase;
import com.dazuizui.basicapi.entry.RedisKey;
import com.dazuizui.basicapi.entry.User;
import com.dazuizui.business.mapper.ContestMapper;
import com.dazuizui.business.mapper.LanguageCommandMapper;
import com.dazuizui.business.service.onlineJudge.LanguageCommandService;
import com.dazuizui.business.util.RedisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;

@SpringBootTest
class BusinessApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisUtil redisUtil;

    @Test
    void contextLoads() {
        //建立连接
        //System.out.println(redisTemplate.opsForValue().get(RedisKey.QuestionCountWithAnyStatus));
        User user = new User();
        user.setUsername("y51288033");
        redisTemplate.opsForValue().set(RedisKey.ZuiBlogUserId+"1",user);
        redisTemplate.opsForValue().set(RedisKey.ZuiBlogUserUsername+"y51288033",user);
        user.setUsername("Liugang");
        redisTemplate.opsForValue().set(RedisKey.ZuiBlogUserId+"666",user);
        redisTemplate.opsForValue().set(RedisKey.ZuiBlogUserUsername+"Liugang",user);

    }


    @Test
    void test1(){
        stringRedisTemplate.opsForValue().increment("c",1);
        System.err.println(stringRedisTemplate.opsForValue().get("c"));

    }
}
