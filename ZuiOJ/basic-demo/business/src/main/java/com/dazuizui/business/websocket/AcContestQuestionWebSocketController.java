package com.dazuizui.business.websocket;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.service.onlineJudge.AcContestQuestionSerivce;
import com.dazuizui.business.util.SpringContextUtil;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;

@CrossOrigin
@ServerEndpoint("/api/zuioj/codingLog/{contest_id}")
@EnableScheduling
public class AcContestQuestionWebSocketController {

    private AcContestQuestionSerivce acContestQuestionSerivce = SpringContextUtil.getBean(AcContestQuestionSerivce.class);


    @OnOpen
    public void onOpen(Session session, @PathParam("contest_id") Long contestId) throws IOException, InterruptedException, EncodeException {

        HashMap<String,Object> map = this.getContestDate(session, contestId);
        session.getBasicRemote().sendText(JSONArray.toJSONString(map));

       // session.getBasicRemote().sendText(JSONArray.toJSONString(null));
        //acContestQuestionSerivce.querySubmitLogByContestIdOrderUpdateTimeDesc(contestId);
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("closed");
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException, EncodeException, InterruptedException {
        Long contestId = Long.valueOf(message);
        HashMap<String,Object> contestDate = this.getContestDate(session, contestId);
        session.getBasicRemote().sendText(JSONArray.toJSONString(contestDate));
    }

    /**
     * todo获取比赛数据
     */
    private HashMap<String,Object> getContestDate(Session session, Long contestId){
        ResponseVo responseVo = acContestQuestionSerivce.querySubmitLogByContestIdOrderUpdateTimeDesc(contestId);

        return (HashMap<String,Object>) responseVo.getData();
    }
}
