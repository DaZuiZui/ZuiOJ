package com.dazuizui.business.contest;

import com.dazuizui.business.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ContestTest {
    @Autowired
    private RedisUtil redisUtil;
    @Test
    public void findById(){
        System.out.println(redisUtil.getStringInRedis("ZuiOJ:Contest:81"));
    }
}
