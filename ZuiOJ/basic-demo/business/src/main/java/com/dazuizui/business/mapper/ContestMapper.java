package com.dazuizui.business.mapper;


import com.dazuizui.basicapi.entry.Contest;
import com.dazuizui.basicapi.entry.QuestionBank;
import com.dazuizui.business.domain.vo.AdminGetArticleByPaginVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
     * @author Yida Yang(杨易达)
     * 修改比赛信息
     * @param contest 比赛信息实体
     */
    public Long updateContest(Contest contest);

    /**
     * @author Yida Yang(杨易达)
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
     * @authro Bryan Yang(Dazui)
     * 管理员分页查询比赛数据
     * Admin query game data by page
     *    根据AdminQueryGameInformationByPageVo查询条件进行分页查询
     * @param adminQueryGameInformationByPageBo 分页
     * @return List<Contest>
     */
    public List<Contest> adminQueryGameInformationByPage(AdminGetArticleByPaginVo.AdminQueryGameInformationByPageVo adminQueryGameInformationByPageBo);

    /**
     * @author Bryan Yang（杨易达）
     * 插入比赛记录
     * @param conTest   比赛实体信息
     * @return long
     */
    public Long insertConTest(Contest conTest);

    /**
     * 插入比赛详细记录
     * @param conTest 比赛实体信息
     * @return
     */
    public Long insertConTestDetailed(Contest conTest);

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
