package com.dazuizui.business.mapper;

import com.dazuizui.basicapi.entry.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    /**
     * 用户登入
     * @param user
     * @return
     */
    public User userLogin(User user);
}
