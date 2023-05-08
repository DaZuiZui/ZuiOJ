package com.dazuizui.business.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 文章详细数量属性Mapper
 */
@Mapper
public interface ArticleAttributeMapper {
    /**
     * 获取指定状态文章的数量
     * @param status
     * @return
     */
    public Long queryCountByStatus(@Param("status")Integer status);

    /**
     * 增加
     * @param status
     * @param num
     * @param type
     * @return
     */
    public Long increaseTheNumberOfTable(@Param("status")Integer status,@Param("num") Long num,@Param("type")Integer type);



}
