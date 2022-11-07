package com.dazuizui.business.controller;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.User;
import com.dazuizui.business.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户模板登入
 */
@CrossOrigin
@RequestMapping("/user")
@RestController
@Api(value = "用户模板控制器",tags = {"用户模板控制器"})
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


    /**
     * 解析token
     * @param token
     * @return
     */
    @ApiOperation("解析用户token")
    @GetMapping("/analysis")
    public String analysis(@RequestParam("token")String token){
        return userService.analysis(token);
    }


    /**
     * 注册账号
     * @param user
     * @return
     */
    @ApiOperation("注册用户")
    @PostMapping("/register")
    public String register(@RequestBody User user){
        return userService.register(user);
    }
}
