package com.dazuizui.business.service.user;

import com.dazuizui.basicapi.entry.User;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public interface UserService {
    /**
     * 通过id查询用户
     * @param id
     * @return
     */
    public User queryUserById(@Param("id") Long id);

    /**
     * 用户登入
     * @param user
     * @return
     */
    public String userLogin(@RequestBody User user);


    /**
     * 解析token
     * @param token
     * @return
     */
    public String analysis(@RequestParam("token")String token);


    /**
     * 用户注册
     * @param user
     * @return
     */
    public String register( User user);
}
