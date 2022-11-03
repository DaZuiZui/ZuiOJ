package com.dazuizui.business.mapper;

import com.dazuizui.basicapi.entry.Contest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface ContestMapper {
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
}
