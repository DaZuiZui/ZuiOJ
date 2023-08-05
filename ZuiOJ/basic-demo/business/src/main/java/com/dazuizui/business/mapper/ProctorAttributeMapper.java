package com.dazuizui.business.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProctorAttributeMapper {
    /**
     * 增加监考数量
     * @param contestId
     * @param number
     * @return
     */
    public Long increaseTheNumberOfProctors(@Param("contestId")Long contestId,@Param("number")Long number);


    /**
     * 创建监考数量
     * @param contestId
     * @return Long
     */
    public Long createAttributeOfProctors(@Param("contestId")Long contestId);
}
