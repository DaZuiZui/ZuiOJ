package com.dazuizui.business.mapper;


import com.dazuizui.basicapi.entry.Contest;
import com.dazuizui.basicapi.entry.QuestionBank;
import com.dazuizui.business.domain.bo.AdminQueryGameInformationByPageBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Mapper
public interface ContestMapper {
    /**
     * 移除比赛简介页面
     */
    public Long removeTheContestById(@Param("id")Long id);

    /**
     * 移除比赛详细简介页面
     */
    public Long removeTheContestDetailedById(@Param("id")Long id);

    /**
     * 修改比赛信息
     */
    public Long updateContest(Contest contest);

    /**
     * 修改比赛详细信息
     * @param contest
     * @return
     */
    public Long updateContestDetailed(Contest contest);

    /**
     * 查询一共多少个比赛，等待优化
     * @return
     */
    public Long queryCoubtOfContest();

    /**
     * 管理员分页查询数据
     * @param adminQueryGameInformationByPageBo
     * @return
     */
    public List<Contest> adminQueryGameInformationByPage(AdminQueryGameInformationByPageBo adminQueryGameInformationByPageBo);

    /**
     * 插入比赛记录
     * @param conTest
     * @return
     */
    public long insertConTest(Contest conTest);

    /**
     * 比赛详细记录
     * @param conTest
     * @return
     */
    public long insertConTestDetailed(Contest conTest);

    /**
     * 通过id
     * @param id
     * @return
     */
    public Contest queryTheContestById(@Param("id")long id);

    /**
     * 获取未来赛事
     * @return
     */
    public List<Contest> getFutureEvents();

    /**
     * 获取往期赛事
     * @return
     */
    public List<Contest> getPastevents();


    /**
     * 通过id获取赛事
     * @param id
     * @return
     */
    public Contest getEventById(@Param("id")Long id);


    /**
     * 通过id获取赛事题目
     * @param id
     * @return
     */
    public List<QuestionBank> getQuestionListInContest(@Param("id")Long id);
}
