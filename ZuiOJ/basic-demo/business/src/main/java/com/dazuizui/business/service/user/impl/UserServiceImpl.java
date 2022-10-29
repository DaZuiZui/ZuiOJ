package com.dazuizui.business.service.user.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.User;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.mapper.UserMapper;
import com.dazuizui.business.service.user.UserService;
import com.dazuizui.business.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        //todo 判断账号密码是否正确
        System.out.println(userInDB);
        return JSONArray.toJSONString(new ResponseVo<>("登入成功",userInDB,"0x0001"));
    }
}
