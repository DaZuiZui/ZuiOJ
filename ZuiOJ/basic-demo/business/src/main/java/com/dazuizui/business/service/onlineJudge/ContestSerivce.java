package com.dazuizui.business.service.onlineJudge;

import com.dazuizui.basicapi.entry.CompetitionInfo;
import com.dazuizui.basicapi.entry.Contest;
import com.dazuizui.business.domain.bo.AdminQueryGameInformationByPageBo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * @author yida yang
 * 竞赛模块接口实现类
 */
@Service
public interface ContestSerivce {
    /**
     * 管理员分页查询数据
     * @param adminQueryGameInformationByPageBo
     * @return
     */
    public String adminQueryGameInformationByPage(AdminQueryGameInformationByPageBo adminQueryGameInformationByPageBo);
    /**
     * 提交赛制
     * @param conTest
     * @return
     */
    public String postContest(Contest conTest);

    /**
     * 获取未来赛制
     * @return
     */
    public String getFutureEvents();


    /**
     * 获取全部赛制
     * @return
     */
    public String getAllEvents();

    /**
     * 比赛选手举报
     * @param reportMessageText
     * @return
     */
    public String competitorReport(@RequestParam("reportMessageText") String reportMessageText);
    /**
     * 通过id获取赛事
     * @param id
     * @return
     */
    public String getEventById(@RequestParam("id")Long id);

    /**
     * 报名比赛
     * @param contestId
     * @return
     */
    public String signUpForTheCompetition(Long contestId);


}
