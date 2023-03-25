package com.dazuizui.business.controller;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.StatusCodeMessage;
import com.dazuizui.basicapi.entry.UpdateUserInfoByIdBo;
import com.dazuizui.basicapi.entry.User;
import com.dazuizui.basicapi.entry.bo.DeleteUserByIdBo;
import com.dazuizui.basicapi.entry.bo.DeleteUsersInBulkBo;
import com.dazuizui.basicapi.entry.bo.PagingToGetUserDateBo;
import com.dazuizui.basicapi.entry.bo.TombstoneUserByIdBo;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.service.user.UserService;
import com.dazuizui.business.util.ThreadLocalUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
     * 查询网站管理人员
     * @return
     */
    @ApiOperation("查询网站管理人员")
    @GetMapping("/admin/queryListOfAdmin")
    public String queryListOfAdmin(@RequestParam("token")String token){
        return userService.queryListOfAdmin();
    }

    /**
     * 通过id物理删除用户
     * @param deleteUserByIdBo
     * @return
     */
    @ApiOperation("物理删除")
    @PostMapping("/admin/deleteUserById")
    public String deleteUserById(@RequestBody DeleteUserByIdBo deleteUserByIdBo){
        //查看是否权限不足
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        if (map.get("error") != null){
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }


        return userService.deleteUserById(deleteUserByIdBo);
    }

    /**
     * 用户登入
     * @return
     */
    @ApiOperation("用户登入")
    @PostMapping("/userlogin")
    public String userLogin(@RequestBody User user){
        System.out.println(user);
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
     * @return  阿斯顿
     */
    @ApiOperation("注册用户")
    @PostMapping("/register")
    public String register(@RequestBody User user){

        return userService.register(user);
    }

    /**
     * 分页获取用户数据
     * @return
     */
    @ApiOperation("分页获取数据")
    @PostMapping("/admin/pagingToGetUserDate")
    public String pagingToGetUserDate(@RequestBody PagingToGetUserDateBo pagingToGetUserDateBo){
        //查看是否权限不足
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        if (map.get("error") != null){
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return userService.pagingToGetUserDate(pagingToGetUserDateBo);
    }

    /**
     * 批量删除用户
     * @return
     */
    @ApiOperation("批量删除用户数据")
    @PostMapping("/admin/deleteUsersInBulk")
    public String deleteUsersInBulk(@RequestBody DeleteUsersInBulkBo deleteUsersInBulkBo){
        //查看是否权限不足
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        if (map.get("error") != null){
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return userService.deleteUsersInBulk(deleteUsersInBulkBo);
    }

    /**
     * 逻辑删除用户通过id
     */
    @ApiOperation("逻辑删除用户通过id")
    @PostMapping("/admin/tombstoneUserById")
    public String tombstoneUserById(@RequestBody TombstoneUserByIdBo tombstoneUserByIdBo){
        System.err.println(tombstoneUserByIdBo);
        //查看是否权限不足
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        if (map.get("error") != null){
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return userService.tombstoneUserById(tombstoneUserByIdBo);
    }

    /**
     * 通过token获取登入者的详细信息
     * @param token
     * @return String.class
     */
    @ApiOperation("通过token获取用户全部信息")
    @GetMapping("/getuserinfoByToken")
    public String getUserInfoByToken(@RequestParam("token")String token){
        return userService.getUserInfoByToken(token);
    }

    /**
     * 通过id修改用户信息
     * @param updateUserInfoByIdBo
     * @return
     */
    @ApiOperation("修改用户信息根据id")
    @PostMapping("/updateUserInfoById")
    public String updateUserInfoById(@RequestBody UpdateUserInfoByIdBo updateUserInfoByIdBo){
        return userService.updateUserInfoById( updateUserInfoByIdBo);
    }
}
