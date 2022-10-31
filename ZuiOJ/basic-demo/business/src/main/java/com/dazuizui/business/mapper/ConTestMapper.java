package com.dazuizui.business.mapper;

import com.dazuizui.basicapi.entry.ConTest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConTestMapper {
    /**
     * 插入比赛记录
     * @param conTest
     * @return
     */
    public long insertConTest(ConTest conTest);

    /**
     * 比赛详细记录
     * @param conTest
     * @return
     */
    public long insertConTestDetailed(ConTest conTest);
}
