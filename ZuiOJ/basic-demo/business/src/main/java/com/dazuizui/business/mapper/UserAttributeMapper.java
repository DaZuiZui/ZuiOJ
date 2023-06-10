package com.dazuizui.business.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserAttributeMapper {
    /**
     * 获取指定状态的用数量
     * @param role
     * @return
     */
    @Select("select number from user_attribute where role = #{role}")
    public Long findNumberOfUsersByRole(@Param("role")Integer role);

    /**
     * 增加用户数量
     */
    public Long increaseTheNumberOfUserByRole(@Param("role") Integer role,@Param("number")Integer number);
}
