package com.dazuizui.business.websocket;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.vo.FindAcCountEveryQuestionByContestIdAndStatusVo;
import com.dazuizui.business.service.onlineJudge.AcContestQuestionSerivce;
import com.dazuizui.business.service.onlineJudge.CompetitionInfoService;
import com.dazuizui.business.util.SpringContextUtil;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;

@Component
@CrossOrigin
@ServerEndpoint("/api/zuioj/contestperson/{contest_id}")
@EnableScheduling
public class QueryCountByContestIdWebSocket {
    private CompetitionInfoService competitionInfoService = SpringContextUtil.getBean(CompetitionInfoService.class);

    @OnOpen
    public void onOpen(Session session, @PathParam("contest_id") Long contestId) throws IOException, InterruptedException, EncodeException {
        System.err.println(contestId+"asdasda");
        Long contestDate = this.getContestDate(session, contestId);
        session.getBasicRemote().sendText(JSONArray.toJSONString(contestDate));


    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("close1d");
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException, EncodeException, InterruptedException {
        Long contestId = Long.valueOf(message);
        Long contestDate = this.getContestDate(session, contestId);
        session.getBasicRemote().sendText(JSONArray.toJSONString(contestDate));
    }

    /**
     * todo获取比赛数据
     */
    private Long getContestDate(Session session, Long contestId){
        ResponseVo responseVo = competitionInfoService.queryCountByContestId(contestId);

        return (Long) responseVo.getData();

    }
}
