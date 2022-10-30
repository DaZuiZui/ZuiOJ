package com.dazuizui.business.service.user.impl;

import cn.hutool.jwt.JWTUtil;
import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.User;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.mapper.UserMapper;
import com.dazuizui.business.service.user.UserService;
import com.dazuizui.business.util.JwtUtil;
import com.dazuizui.business.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户登入接口实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 用户登入
     * @param user
     * @return
     */
    @Override
    public String userLogin(User user) {
        //查看账号密码是否正确
        User userInDB = (User) redisUtil.getStringInRedis("DaZuiOj:user:info:"+user.getUsername());
        if (userInDB == null){
            userInDB = userMapper.userLogin(user);
            if (userInDB == null){
                return JSONArray.toJSONString(new ResponseVo<>("账号不存在",null,"0x0002"));
            }
        }

        //查看是否密码正确
        if (!userInDB.getPassword().equals(user.getPassword())){
            return JSONArray.toJSONString(new ResponseVo<>("密码错误",null,"0x0003"));
        }
        //生成JWT
        String jwt = JwtUtil.createJWT(user);
        //封装返回
        Map<String,Object> map = new HashMap<>();
        map.put("jwt",jwt);
        map.put("userinfo",userInDB);
        return JSONArray.toJSONString(new ResponseVo<>("登入成功",map,"0x0001"));
    }
}
