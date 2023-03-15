package com.dazuizui.business.mapper;

import com.dazuizui.basicapi.entry.CompetitionInfo;
import com.dazuizui.basicapi.entry.GetTotal;
import com.dazuizui.basicapi.entry.Ranking;
import com.dazuizui.basicapi.entry.User;
import com.dazuizui.business.domain.CompetitionInfoInContest;
import com.dazuizui.business.domain.bo.AdminAddCompetitionInfoBo;
import com.dazuizui.business.domain.bo.PaglingQueryContestantsInThisContestBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * @author yangyida
 * 比赛选手持久层
 */
@Mapper
public interface CompetitionInfoMapper {
    /**
     * 生成Rediskey
     * @param contestId
     * @return
     */
    public List<String> queryCompettionInfoRedisKeyByContestId(@Param("contestId")Long contestId);
    /**
     * 删除所有比赛选手通过比赛Id
     */
    public Long deleteAllCompetitionInfoByContestId(@Param("contestId") Long contestId);

    /**
     * 查看用户是否参赛
     * @return
     */
    public CompetitionInfo checkForEntryByContestIdAnd(@Param("contestId")Long contestId, @Param("userId")Long id);

    /**
     * 管理员添加比赛选手信息
     * @param contestId
     * @param id
     * @return
     */
    public Long adminAddCompetitionInfo(@Param("contestId")Long contestId, @Param("userId")Long id);

    /**
     * 查询参赛选手的数量
     * todo 因为赶项目，等忙完主线开发在回来优化
     * @param contestId
     * @return
     */
    @Select("select count(id) from competition_info where contest_id = #{contestId}")
    public Long queryConQueryTheNumberOfContestantstest(@Param("contestId")Long contestId);

    /**
     * 分页查询参赛选手信息在当前比赛
     * @param paglingQueryContestantsInThisContestBo
     * @return
     */
    public List<CompetitionInfoInContest> paglingQueryContestantsInThisContest(PaglingQueryContestantsInThisContestBo paglingQueryContestantsInThisContestBo);
    /**
     * 通过比赛id查询该比赛的所有选手
     * @param id
     * @return
     */
    @Select("select CONCAT('ZuiOJ:Conetst:CompetitionInfoInContest:Contest:',contest_id,':',user_id) as 'str' from competition_info where contest_id = #{id}")
    public List<String> selectAllUserInTheContestByContestId(@Param("id")Long id);

    /**
     * 删除该比赛的所有比赛选手
     * @param id
     * @return
     */
    public Long deleteCompetitionInfoByContestId(@Param("id")Long id);

    /**
     * 查看用户是否参赛
     * @return
     */
    public CompetitionInfo checkForEntry(CompetitionInfo competitionInfo);

    /**
     * 添加参赛信息
     * @param competitionInfo
     * @return
     */
    public Long addCompetitionInfo(CompetitionInfo competitionInfo);

    /**
     * 获取榜单
     * @param contestId
     * @param page
     * @param numbers
     * @return
     */
    public List<Ranking> viewRanking(@Param("startTime") Date startTime, @Param("contestId") Long contestId, @Param("page") Integer page, @Param("number") Integer numbers) ;

    /**
     * 获取数据个数
     * @param contestId
     * @return
     */
    public GetTotal getTotal(@Param("contestId")Long contestId);
}
