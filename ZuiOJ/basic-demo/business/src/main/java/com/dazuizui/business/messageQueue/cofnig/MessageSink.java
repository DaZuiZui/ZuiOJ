package com.dazuizui.business.messageQueue.cofnig;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/*
自定义消息通道
 */
public interface MessageSink {
    //application.yml中引用此名称
    String AddArticleINPUT = "blog_add_input";
 
    @Input(MessageSink.AddArticleINPUT)
    SubscribableChannel bloginput();

    //application.yml中引用此名称
    String ContestCodeAddINPUT = "contest_code_add_input";

    @Input(MessageSink.ContestCodeAddINPUT)
    SubscribableChannel contestSubmittionCodeinput();
}