package com.dazuizui.business;

import com.dazuizui.basicapi.entry.Attribute;
import com.dazuizui.basicapi.entry.RedisKey;
import com.dazuizui.basicapi.entry.User;
import com.dazuizui.business.mapper.AttributeMapper;
import com.dazuizui.business.messageQueue.blog.config.BlogSource;
import com.dazuizui.business.service.student.StudentService;
import com.dazuizui.business.util.RedisUtil;
import com.dazuizui.business.util.TransactionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

@SpringBootTest
@EnableBinding(BlogSource.class)
class BusinessApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private AttributeMapper attributeMapper;


    @Autowired
    private StudentService studentService;

    @Autowired
    private BlogSource source;


    @Test
    void test1(){
        //System.err.println(source.addArticleOutput().send(MessageBuilder.withPayload("hello").build()));
    }

    @Autowired
    private TransactionUtils transactionUtils;

    @Test
    void test3(){
        TransactionStatus transactionStatus = transactionUtils.begin(TransactionDefinition.ISOLATION_READ_COMMITTED);

        try {
            attributeMapper.IncreaseTheNumberOfTable(1L);
            redisUtil.setStringInRedis("aaaaaa",1000,1231231);
            System.out.println(10/0);
        } catch (Exception e) {
            transactionUtils.rollback(transactionStatus);
            //e.printStackTrace();
            return;
        }


        transactionUtils.commit(transactionStatus);
    }


    @Test
    void contextLoads() {
        System.err.println();
        System.err.println(redisUtil.getStringInRedis("aaaaaa"));
    }
}
