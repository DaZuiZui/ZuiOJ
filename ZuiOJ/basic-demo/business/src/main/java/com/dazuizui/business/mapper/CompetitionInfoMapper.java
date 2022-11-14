package com.dazuizui.business.mapper;

import com.dazuizui.basicapi.entry.CompetitionInfo;
import com.dazuizui.basicapi.entry.GetTotal;
import com.dazuizui.basicapi.entry.Ranking;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface CompetitionInfoMapper {

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
