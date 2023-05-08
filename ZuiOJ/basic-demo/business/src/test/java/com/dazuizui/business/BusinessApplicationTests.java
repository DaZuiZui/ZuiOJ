package com.dazuizui.business;

import com.alibaba.fastjson2.JSONObject;
import com.dazuizui.basicapi.entry.*;
import com.dazuizui.basicapi.entry.bo.GetQuestionAnswerByPageBo;
import com.dazuizui.business.mapper.AttributeMapper;
import com.dazuizui.business.mapper.BlogMapper;
import com.dazuizui.business.mapper.CompetitionInfoMapper;
import com.dazuizui.business.messageQueue.cofnig.MessageSource;
import com.dazuizui.business.service.student.StudentService;
import com.dazuizui.business.util.RedisUtil;
import com.dazuizui.business.util.TransactionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import java.util.List;

@SpringBootTest
@EnableBinding(MessageSource.class)
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
    private MessageSource source;

    @Autowired
    private CompetitionInfoMapper competitionInfoMapper;
    @Test
    void test1(){
       // System.out.println(competitionInfoMapper.selectAllUserInTheContestByContestId(44l));
        System.out.println(redisUtil.getStringInRedis("ZuiOJ:Conetst:CompetitionInfoInContest:Contest:44:1"));
        System.out.println(redisUtil.getStringInRedis("ZuiOJ:Conetst:CompetitionInfoInContest:Contest:44:2"));
    }

    @Autowired
    private TransactionUtils transactionUtils;

    @Test
    void test4(){
        System.out.println(redisTemplate.opsForValue().get("314461eb-9048-4650-9efc-d267753603b8"));
        System.out.println(redisTemplate.delete("314461eb-9048-4650-9efc-d267753603b8"));
        System.out.println(redisTemplate.opsForValue().get("314461eb-9048-4650-9efc-d267753603b8"));
    }

    @Test
    void test3(){
        TransactionStatus transactionStatus = transactionUtils.begin(TransactionDefinition.ISOLATION_READ_COMMITTED);

        try {
            attributeMapper.increaseTheNumberOfTable(1L,1L);
            redisUtil.setStringInRedis("aaaaaa",1000,1231231);
            System.out.println(10/0);
        } catch (Exception e) {
            transactionUtils.rollback(transactionStatus);
            //e.printStackTrace();
            return;
        }


        transactionUtils.commit(transactionStatus);
    }


    /**
     * 分页测试
     */
    @Autowired
    private BlogMapper blogMapper;

    @Test
    void contextLoads() {
        GetQuestionAnswerByPageBo getQuestionAnswerByPageBo = new GetQuestionAnswerByPageBo();
        getQuestionAnswerByPageBo.setQuestionId(1L);
        getQuestionAnswerByPageBo.setDelFlag(0);
        getQuestionAnswerByPageBo.setStatus(0);
        getQuestionAnswerByPageBo.setNumber(2L);
        getQuestionAnswerByPageBo.setStart(0L);
        List<ArticleJSON> questionAnswerByPage = blogMapper.getQuestionAnswerByPage(getQuestionAnswerByPageBo);

        List<Long> list = (List<Long>) JSONObject.parseObject(questionAnswerByPage.get(0).getArticleType(), Object.class);
        System.err.println("\n\n\n\n"+list);
        // questionAnswerByPage.get(0).getArticleType();
        System.out.println(questionAnswerByPage);
    }
}
