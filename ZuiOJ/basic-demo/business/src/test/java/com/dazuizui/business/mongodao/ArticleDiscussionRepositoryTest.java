package com.dazuizui.business.mongodao;

import com.dazuizui.business.messageQueue.blog.config.BlogSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootTest
@EnableBinding(BlogSource.class)
public class ArticleDiscussionRepositoryTest {
    @Autowired
    private ArticleDiscussionRepository articleDiscussionRepository;
    @Test
    void test1(){
        System.out.println(articleDiscussionRepository.findAll());
    }
}
