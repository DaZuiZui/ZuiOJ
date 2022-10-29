package com.dazuizui.business;

import com.dazuizui.basicapi.entry.QuestionCase;
import com.dazuizui.business.mapper.LanguageCommandMapper;
import com.dazuizui.business.service.onlineJudge.LanguageCommandService;
import com.dazuizui.business.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@SpringBootTest
class BusinessApplicationTests {
    @Autowired
    private LanguageCommandService languageCommandService;
    @Autowired
    private LanguageCommandMapper languageCommandMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisUtil redisUtil;
    @Test
    void contextLoads() {
        List<QuestionCase> questionCases = redisUtil.getListInRedis("ZuiOJ:QuestionBack:QuestionID1");
        System.out.println(questionCases);
    }

}
