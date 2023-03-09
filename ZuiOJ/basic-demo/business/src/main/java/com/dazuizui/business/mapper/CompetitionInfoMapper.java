package com.dazuizui.business.mapper;

import com.dazuizui.basicapi.entry.CompetitionInfo;
import com.dazuizui.basicapi.entry.GetTotal;
import com.dazuizui.basicapi.entry.Ranking;
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
     * 通过比赛id查询该比赛的所有选手
     * @param id
     * @return
     */
    @Select("select CONCAT('ZuiOJ:Conetst:CompetitionInfo:Contest:',contest_id,':',user_id) as 'str' from competition_info where contest_id = #{id}")
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
