package com.dazuizui.business.messageQueue.blog.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

/*
自定义消息通道
 */
public interface BlogSink {
    //application.yml中引用此名称
    String AddArticleINPUT = "blog_add_input";
 
    @Input(BlogSink.AddArticleINPUT)
    SubscribableChannel input();
}