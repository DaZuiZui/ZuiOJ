package com.dazuizui.business.websocket;

import com.dazuizui.business.service.onlineJudge.CompetitionInfoService;
import com.dazuizui.business.service.user.UserService;
import com.dazuizui.business.util.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@CrossOrigin
@ServerEndpoint("/api/zuioj/{contest_id}/{page}/{size}")
@Component
public class RandomNumberController {

    private CompetitionInfoService competitionInfoService = SpringContextUtil.getBean(CompetitionInfoService.class);

    @OnOpen
    public void onOpen(Session session, @PathParam("contest_id") Long contestId , @PathParam("page")Integer page , @PathParam("size")Integer size) throws IOException, InterruptedException {
        System.out.println("连接成功" + contestId+" and "+page+" "+size);
        String returnJSON = competitionInfoService.viewRanking(contestId, page, size);
        while (true){
            Thread.sleep(1000);
            session.getBasicRemote().sendText(returnJSON);
        }

    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("closed");
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        session.getBasicRemote().sendText("asd");
    }
}