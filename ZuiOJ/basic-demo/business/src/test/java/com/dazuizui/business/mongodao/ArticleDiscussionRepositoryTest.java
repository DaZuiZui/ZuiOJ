package com.dazuizui.business.mongodao;

import com.dazuizui.business.messageQueue.cofnig.MessageSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;

@SpringBootTest
@EnableBinding(MessageSource.class)
public class ArticleDiscussionRepositoryTest {
    @Autowired
    private ArticleDiscussionRepository articleDiscussionRepository;
    @Test
    void test1(){
        System.out.println(articleDiscussionRepository.findAll());
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
