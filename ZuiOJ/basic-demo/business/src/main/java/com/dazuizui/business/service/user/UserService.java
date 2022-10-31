package com.dazuizui.business.service.user;

import com.dazuizui.basicapi.entry.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public interface UserService {
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
}
