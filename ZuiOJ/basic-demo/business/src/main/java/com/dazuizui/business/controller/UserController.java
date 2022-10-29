package com.dazuizui.business.controller;

import com.alibaba.fastjson2.JSONArray;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户模板登入
 */
@CrossOrigin
@RequestMapping("/user")
@RestController
public class UserController {
    /**
     * 用户登入
     * @return
     */
    @ApiOperation("用户登入")
    @PostMapping("/userlogin")
    public String userLogin(){
        return JSONArray.toJSONString("login");
    }
}
