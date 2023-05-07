package com.dazuizui.business.messageQueue.blog;

import com.dazuizui.business.domain.bo.CreateArticleBo;
import com.dazuizui.business.messageQueue.cofnig.MessageSink;
import com.dazuizui.business.service.blog.LanguageArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(MessageSink.class)
public class MessageConsumer {
    @Autowired
    private LanguageArticleTypeService languageArticleTypeService;

    /**
     * 接收消息
     * @param articleBo
     */
    @StreamListener(MessageSink.AddArticleINPUT)
    public void receive(CreateArticleBo articleBo) {
        //languageArticleTypeService.AddArticleType(articleBo);

        System.out.println("消息 = " + articleBo);
    }
}