package com.dazuizui.business.websocket;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.vo.RankingVo;
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
@ServerEndpoint("/api/zuioj/{contest_id}/{page}/{size}")
@EnableScheduling
public class CompetitionInfoWebSocketController {

    private Integer  page = 1;//当前页面
    private Long contestId;
    private Session session;


    private CompetitionInfoService competitionInfoService = SpringContextUtil.getBean(CompetitionInfoService.class);

    @OnOpen
    public void onOpen(Session session, @PathParam("contest_id") Long contestId , @PathParam("page")Integer page , @PathParam("size")Integer size) throws IOException, InterruptedException, EncodeException {
        this.contestId = contestId;
        this.session   = session;

        RankingVo rankingVo = competitionInfoService.viewRankingReturnList(contestId, this.page-1, size);
        if (session != null){
            session.getBasicRemote().sendText(JSONArray.toJSONString(rankingVo));
        }

    }

    /**
     * 宣传定时任务
     */
    @Scheduled(fixedRate = 1000)
    private void configureTasks() throws EncodeException, IOException, InterruptedException {
        this.onOpen(this.session,this.contestId,this.page,25);
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("closed");
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException, EncodeException, InterruptedException {
        Integer page = Integer.valueOf(message);
        this.page = page;
        this.onOpen(session,this.contestId,this.page,25);
//        session.getBasicRemote().sendText("asd"+message );
    }
}