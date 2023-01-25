package com.dazuizui.business.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AttributeMapper {
    /**
     * 添加表属性数据个数
     */
    public Long IncreaseTheNumberOfTable(@Param("id") Long id);
}
