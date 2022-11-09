package com.dazuizui.business.mapper;

import com.dazuizui.basicapi.entry.CompetitionInfo;
import org.apache.ibatis.annotations.Mapper;

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
}
