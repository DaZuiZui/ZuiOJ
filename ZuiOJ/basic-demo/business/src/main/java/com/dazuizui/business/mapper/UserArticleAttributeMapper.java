package com.dazuizui.business.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用户文章属性
 */
@Mapper
public interface UserArticleAttributeMapper {
    /**
     * 查看该用户数据是否存在
     * @param userid
     * @return
     */
    @Select("select count(*) from user_article_attribute where user_id = #{userId}")
    public Long queryUserByUserId(@Param("userId")Long userId);

    /**
     * 添加用户文章属性
     * @param userId
     * @return
     */
    public Long AddUserArticleAttribute(@Param("userId")Long userId);

    /**
     * 添加用户博文数量
     * @param status 状态
     * @param userId 用户id
     * @param num    增加的数字
     * @param type   类型 1为添加
     * @return
     */
    public Long increaseTheNumberOfUserActicle(@Param("status")Integer status,@Param("userId")Long userId,@Param("num")Long num,@Param("type")Integer type);
}
