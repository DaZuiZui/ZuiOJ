package com.dazuizui.business.messageQueue.blog;

import com.dazuizui.basicapi.entry.bo.CreateArticleBo;
import com.dazuizui.business.messageQueue.blog.config.BlogSink;
import com.dazuizui.business.service.blog.LanguageArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(BlogSink.class)
public class MessageConsumer {
    @Autowired
    private LanguageArticleTypeService languageArticleTypeService;
    /**
     * 接收消息
     * @param articleBo
     */
    @StreamListener(BlogSink.AddArticleINPUT)
    public void receive(CreateArticleBo articleBo) {
        languageArticleTypeService.AddArticleInLanguageArticle(articleBo);
        //System.out.println("消息 = " + articleBo);
    }
}