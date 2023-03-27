package com.dazuizui.business.messageQueue.onlineJudge;

import com.dazuizui.basicapi.entry.bo.CreateArticleBo;
import com.dazuizui.business.domain.CodeInContest;
import com.dazuizui.business.messageQueue.cofnig.MessageSink;
import com.dazuizui.business.service.onlineJudge.CodeInContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * OJ盼题消息队列监听
 */
@Component
@EnableBinding(MessageSink.class)
public class OJMessageConsumer {
    @Autowired
    private CodeInContestService codeInContestService;

    /**
     * 接收消息
     * @param codeInContest
     */
    @StreamListener(MessageSink.ContestCodeAddINPUT)
    public void receive(CodeInContest codeInContest) {
        codeInContestService.insertSubmittionContestCodeInfo(codeInContest);

    }
}