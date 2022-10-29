package com.dazuizui.business.controller;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.User;
import com.dazuizui.business.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户模板登入
 */
@CrossOrigin
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户登入
     * @return
     */
    @ApiOperation("用户登入")
    @PostMapping("/userlogin")
    public String userLogin(@RequestBody User user){
        return userService.userLogin(user);
    }
}
