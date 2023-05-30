package com.dazuizui.business.mongodao;

import com.dazuizui.basicapi.entry.RedisKey;
import com.dazuizui.basicapi.entry.vo.QuestionBankVo;
import com.dazuizui.business.domain.CodeInContest;
import com.dazuizui.business.mapper.SystemMapper;
import com.dazuizui.business.messageQueue.cofnig.MessageSource;

import com.dazuizui.business.util.RedisUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.integration.support.MessageBuilder;

@SpringBootTest
@EnableBinding(MessageSource.class)
public class ArticleDiscussionRepositoryTest {
    @Autowired
    private ArticleDiscussionRepository articleDiscussionRepository;
    @Autowired
    private SubmmitionCodeInContestRepository submmitionCodeInContestRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private SystemMapper systemMapper;

    @Test
    void test0(){
        systemMapper.supportGroupSorting();
    }

    @Test
    void test1(){
        Page<CodeInContest> byContestIdAndQuestionIdAndUserId = submmitionCodeInContestRepository
                .findByContestIdAndQuestionIdAndUserIdAndDelFlag(49l, 49l, 1l,0, PageRequest.of(0, 2));
        System.out.println(byContestIdAndQuestionIdAndUserId.getContent());
        System.out.println(byContestIdAndQuestionIdAndUserId.getTotalElements());
    }

    @Autowired
    private MessageSource source;

    @Autowired
    private RedisUtil redisUtil;
    @Test
    void test2(){
        //        source.addArticleOutput().send(MessageBuilder.withPayload(articleBo).build());
        //  source.addArticleOutput().send(MessageBuilder.withPayload(new CreateArticleBo()).build());
       // boolean send = source.addContestSubmittionCodeOutput().send(MessageBuilder.withPayload("Hello").build());
        QuestionBankVo questionBankVo = (QuestionBankVo) redisUtil.getStringInRedis(RedisKey.ZuiOJQuestion+10111);
        System.out.println(questionBankVo );
    }

    @Test
    void test3(){
        Long aLong = articleDiscussionRepository.deleteByQuestionId(10084l);
        System.out.println(aLong);
    }
}
