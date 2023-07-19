package com.dazuizui.business.mapper;

import com.dazuizui.business.domain.TeamInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 比赛成员信息
 */
@Mapper
public interface TeamInfoMapper {
    /**
     * 通过Excel插入比赛信息
     * @return
     */
    public Long insertTeamInfo(TeamInfo teamInfo);
}
