package com.dazuizui.business.mapper;

import com.dazuizui.business.domain.Proctor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProctorMapper {
    /**
     * 添加监考信息
     * @param proctor
     * @return
     */
    public Long addProctor(Proctor proctor);

    /**
     * 检测是否为监考人员
     * @param contestId
     * @param userId
     * @return
     */
    public Proctor findByContestIdAndUserId(@Param("contestId") Long contestId,@Param("userId") Long userId);
}
