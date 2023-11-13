package com.dazuizui.business.websocket;


import cn.hutool.core.lang.hash.Hash;
import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.AcContestQuestion;
import com.dazuizui.basicapi.entry.vo.RankingVo;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.vo.GetContestDateVo;
import com.dazuizui.business.service.onlineJudge.AcContestQuestionSerivce;
import com.dazuizui.business.service.onlineJudge.CompetitionInfoService;
import com.dazuizui.business.service.onlineJudge.ContestSerivce;
import com.dazuizui.business.util.SpringContextUtil;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.swing.*;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@CrossOrigin
@ServerEndpoint("/api/zuioj/codingLog/{contest_id}")
@Component
@EnableScheduling
public class AcContestQuestionWebSocketController {

    private AcContestQuestionSerivce acContestQuestionSerivce = SpringContextUtil.getBean(AcContestQuestionSerivce.class);
    private ContestSerivce contestSerivce = SpringContextUtil.getBean(ContestSerivce.class);

    @OnOpen
    public void onOpen(Session session, @PathParam("contest_id") Long contestId) throws IOException, InterruptedException, EncodeException {
        System.err.println("接受"+contestId);
        List<AcContestQuestion> contestDate = this.getContestDate(session, contestId);
        session.getBasicRemote().sendText(JSONArray.toJSONString(contestDate));

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
        List<AcContestQuestion> contestDate = this.getContestDate(session, contestId);
        session.getBasicRemote().sendText(JSONArray.toJSONString(contestDate));
    }

    /**
     * todo获取比赛数据
     */
    private List<AcContestQuestion> getContestDate(Session session, Long contestId){
        ResponseVo responseVo = acContestQuestionSerivce.querySubmitLogByContestIdOrderUpdateTimeDesc(contestId);

        return (List<AcContestQuestion>) responseVo.getData();
    }
}
