package com.dazuizui.business.service.onlineJudge;

import com.dazuizui.basicapi.entry.Contest;
import com.dazuizui.business.domain.vo.AdminGetArticleByPaginVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;


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
     * @author Bryan Yang(杨易达 Dazui)
     * 修改比赛信息
     *    修改了比赛的简介信息和详细信息和更新redis事务
     *
     * Modify contest info
     *    modifyed the profile info and details of the contet and updated the redis data
     *
     * @param contest 比赛数据实体
     * @return
     */
    public String updateContest(Contest contest);

    /**
     * @authro Bryan Yang(Dazui)
     *
     * 管理员分页查询比赛数据
     * Admin query game data by page
     *
     *    根据AdminQueryGameInformationByPageVo查询条件进行分页查询
     *
     *     Perform pagination query according to AdminQueryGameInformationByPageVo query conditions
     *
     * @param adminQueryGameInformationByPageBo 分页
     * @return String
     */
    public String adminQueryGameInformationByPage(AdminGetArticleByPaginVo.AdminQueryGameInformationByPageVo adminQueryGameInformationByPageBo);

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
     * @author Bryan Yang(Dazui)
     * 获取全部赛制
     *
     * 业务层
     *  获取未来进行时赛事和以前进行时赛事。
     *
     * Business Layer
     *   Get future on-going events and previous on-going events.
     *
     * @return String
     */
    public String getAllEvents();

    /**
     * 比赛选手举报
     * @param reportMessageText
     * @return
     */
    public String competitorReport(@RequestParam("reportMessageText") String reportMessageText);

    /**
     * @author 03/10/2022  Bryan
     *
     * 通过id获取赛制
     * get event by id
     * @param id 赛制id
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
