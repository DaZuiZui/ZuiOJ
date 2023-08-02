package com.dazuizui.business.service.onlineJudge;

import com.dazuizui.basicapi.entry.CompetitionInfo;
import com.dazuizui.basicapi.entry.Contest;
import com.dazuizui.business.domain.bo.AdminQueryGameInformationByPageBo;
import org.apache.ibatis.annotations.Param;
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
     * 移除比赛信息
     * remove the competition
     * @param id 比赛id competition ID
     *
     *    在进行删除了比赛的简介信息和比赛的详细信息和该考场的监考人员和redis中缓存的比赛内容信息数据和所有参赛选手的记录。
     *
     *    on the business layer,the profile info and detailed info of the competition,the invigilators of the
     *    examination room ,the contest info date of competition cached in redis and records of contestands are deleted
     *
     * @Param id 比赛id
     */
    public String removeTheContestById(@Param("id")Long id);

    /**
     * 修改比赛信息
     * @param contest
     * @return
     */
    public String updateContest(Contest contest);
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
