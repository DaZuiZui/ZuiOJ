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

    /**
     * 校验用户名和学号是否唯一
     * @param user
     * @return
     */
    public User checkUsernameAndStudentId(User user);

    /**
     * 注册用户
     * @param user
     * @return
     */
    public Long register(User user);
}
