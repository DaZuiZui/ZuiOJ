package com.dazuizui.business;

import com.dazuizui.basicapi.entry.Contest;
import com.dazuizui.basicapi.entry.QuestionCase;
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

import java.util.List;

@SpringBootTest
class BusinessApplicationTests {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Test
    void contextLoads() {
        //建立连接
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            //获取mapper文件
            ContestMapper contestMapper = sqlSession.getMapper(ContestMapper.class);

            sqlSession.close();

            //增 删 改 查 service
            Contest contest = new Contest();
            contest.setId(1);
            contest.setContestType(1);
            contest.setName("123");
            contestMapper.insertConTest(contest);
            System.out.println(contest);

            //close session with mysql

        };

    }

}
