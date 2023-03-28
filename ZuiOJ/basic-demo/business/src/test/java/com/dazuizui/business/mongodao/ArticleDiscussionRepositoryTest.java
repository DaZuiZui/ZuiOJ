package com.dazuizui.business.mongodao;

import com.dazuizui.business.domain.CodeInContest;
import com.dazuizui.business.messageQueue.cofnig.MessageSource;

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
    @Test
    void test1(){
        Page<CodeInContest> byContestIdAndQuestionIdAndUserId = submmitionCodeInContestRepository
                .findByContestIdAndQuestionIdAndUserIdAndDelFlag(49l, 49l, 1l,0, PageRequest.of(0, 2));
        System.out.println(byContestIdAndQuestionIdAndUserId.getContent());
        System.out.println(byContestIdAndQuestionIdAndUserId.getTotalElements());
    }

    @Autowired
    private MessageSource source;

    @Test
    void test2(){
        //        source.addArticleOutput().send(MessageBuilder.withPayload(articleBo).build());
        //  source.addArticleOutput().send(MessageBuilder.withPayload(new CreateArticleBo()).build());
        boolean send = source.addContestSubmittionCodeOutput().send(MessageBuilder.withPayload("Hello").build());
        System.out.println("????"+send);
    }
}
