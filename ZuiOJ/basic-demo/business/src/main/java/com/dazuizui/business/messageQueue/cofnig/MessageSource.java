package com.dazuizui.business.messageQueue.cofnig;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 博客消息队列
 */
public interface MessageSource {
    String AddArticleOutput = "blog_add_output";

    /**
     * 添加博文消息队列
     * @return
     */
    @Output(MessageSource.AddArticleOutput)
    MessageChannel addArticleOutput();


    String ContestCodeAddOutput = "contest_code_add_output";
    /**
     * 添加博文消息队列
     * @return
     */
    @Output(MessageSource.ContestCodeAddOutput)
    MessageChannel addContestSubmittionCodeOutput();
}