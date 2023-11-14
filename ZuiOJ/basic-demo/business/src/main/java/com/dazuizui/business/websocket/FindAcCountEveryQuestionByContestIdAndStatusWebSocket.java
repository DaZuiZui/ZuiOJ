package com.dazuizui.business.websocket;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.vo.FindAcCountEveryQuestionByContestIdAndStatusVo;
import com.dazuizui.business.service.onlineJudge.AcContestQuestionSerivce;
import com.dazuizui.business.service.onlineJudge.SubmmitionCodeInContestSerivce;
import com.dazuizui.business.util.SpringContextUtil;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@ServerEndpoint("/api/zuioj/AcQuestion/{contest_id}")
@Component
@EnableScheduling
public class FindAcCountEveryQuestionByContestIdAndStatusWebSocket {
    private AcContestQuestionSerivce  acContestQuestionSerivce= SpringContextUtil.getBean(AcContestQuestionSerivce.class);

    @OnOpen
    public void onOpen(Session session, @PathParam("contest_id") Long contestId) throws IOException, InterruptedException, EncodeException {
        System.err.println(contestId);
        List<FindAcCountEveryQuestionByContestIdAndStatusVo> contestDate = this.getContestDate(session, contestId);
        session.getBasicRemote().sendText(JSONArray.toJSONString(contestDate));
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("close1d");
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException, EncodeException, InterruptedException {
        Long contestId = Long.valueOf(message);
        List<FindAcCountEveryQuestionByContestIdAndStatusVo> contestDate = this.getContestDate(session, contestId);
        session.getBasicRemote().sendText(JSONArray.toJSONString(contestDate));
    }

    /**
     * todo获取比赛数据
     */
    private List<FindAcCountEveryQuestionByContestIdAndStatusVo> getContestDate(Session session, Long contestId){
        ResponseVo responseVo = acContestQuestionSerivce.findAcCountEveryQuestionByContestIdAndStatus(contestId);

        return (List<FindAcCountEveryQuestionByContestIdAndStatusVo>) responseVo.getData();

    }
}
