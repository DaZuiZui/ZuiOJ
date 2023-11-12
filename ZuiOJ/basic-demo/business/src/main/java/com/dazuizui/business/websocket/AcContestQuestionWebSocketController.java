package com.dazuizui.business.websocket;


import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.vo.RankingVo;
import com.dazuizui.business.service.onlineJudge.AcContestQuestionSerivce;
import com.dazuizui.business.service.onlineJudge.CompetitionInfoService;
import com.dazuizui.business.util.SpringContextUtil;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@CrossOrigin
@ServerEndpoint("/api/zuioj/codingLog/{contest_id}")
@Component
@EnableScheduling
public class AcContestQuestionWebSocketController {

    private AcContestQuestionSerivce acContestQuestionSerivce = SpringContextUtil.getBean(AcContestQuestionSerivce.class);

    @OnOpen
    public void onOpen(Session session, @PathParam("contest_id") Long contestId) throws IOException, InterruptedException, EncodeException {
        System.err.println("!23");
       // session.getBasicRemote().sendText(JSONArray.toJSONString(null));
        acContestQuestionSerivce.querySubmitLogByContestIdOrderUpdateTimeDesc(contestId);
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("closed");
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException, EncodeException, InterruptedException {
        Long contestId = Long.valueOf(message);
        System.err.println("!1231231");
        session.getBasicRemote().sendText("assd");
//        session.getBasicRemote().sendText("asd"+message );
    }
}
