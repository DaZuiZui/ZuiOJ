package com.dazuizui.business;

import com.dazuizui.basicapi.entry.RedisKey;
import com.dazuizui.basicapi.entry.User;
import com.dazuizui.business.messageQueue.blog.config.BlogSource;
import com.dazuizui.business.service.student.StudentService;
import com.dazuizui.business.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.integration.support.MessageBuilder;

@SpringBootTest
@EnableBinding(BlogSource.class)
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


    @Autowired
    private StudentService studentService;

    @Autowired
    private BlogSource source;


    @Test
    void test1(){
        System.err.println(source.addArticleOutput().send(MessageBuilder.withPayload("hello").build()));
    }
}
