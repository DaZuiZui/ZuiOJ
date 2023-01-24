package com.dazuizui.business.messageQueue.blog.config;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

/**
 * 博客消息队列
 */
public interface BlogSource {
    String AddArticleOutput = "blog_add_output";

    /**
     * 添加博文消息队列
     * @return
     */
    @Output(BlogSource.AddArticleOutput)
    MessageChannel addArticleOutput();
}