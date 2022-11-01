package com.dazuizui.business.mapper;

import com.dazuizui.basicapi.entry.Contest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ConTestMapper {
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
}
