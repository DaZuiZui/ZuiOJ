package com.dazuizui.business.messageQueue.onlineJudge;

import com.dazuizui.basicapi.entry.bo.CreateArticleBo;
import com.dazuizui.business.messageQueue.cofnig.MessageSink;
import com.dazuizui.business.service.blog.LanguageArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(MessageSink.class)
public class OJMessageConsumer {

    /**
     * 接收消息
     * @param str
     */
    @StreamListener(MessageSink.ContestCodeAddINPUT)
    public void receive(String str) {

        System.out.println("消息 = " + str);
    }
}