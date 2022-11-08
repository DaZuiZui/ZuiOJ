package com.dazuizui.business.service.user.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.User;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.mapper.UserMapper;
import com.dazuizui.business.service.user.UserService;
import com.dazuizui.business.util.JwtUtil;
import com.dazuizui.business.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        User userInDB = null; //(User) redisUtil.getStringInRedis("DaZuiOj:user:info:"+user.getUsername());
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
        String jwt = JwtUtil.createJWT(userInDB);
        //封装返回
        Map<String,Object> map = new HashMap<>();
        map.put("jwt",jwt);
        map.put("userinfo",userInDB);
        return JSONArray.toJSONString(new ResponseVo<>("登入成功",map,"0x0001"));
    }

    /**
     * 解析token
     * @param token
     * @return
     */
    @Override
    public String analysis(String token) {
        Map<String, Object> map = null;
        try {
            map = JwtUtil.analysis(token);
        } catch (Exception e) {
            return JSONArray.toJSONString(new ResponseVo<>("身份验证已过期",null,"0x0005"));
        }

        User user = new User();
        System.out.println(map.get("id"));
        user.setId((Integer) map.get("id"));
        user.setUsername((String) map.get("username"));
        user.setStudentId((String) map.get("studentId"));
        user.setName((String) map.get("name"));
        user.setSex((Integer) map.get("sex"));
        user.setIdCard((String) map.get("idCard"));
        user.setCollegeId((Integer) map.get("collegeId"));
        user.setMajorId((Integer) map.get("majorId"));
        user.setClassId((Integer) map.get("classId"));
        user.setRole((Integer) map.get("role"));
        user.setGrade((Integer) map.get("grade"));
        user.setStatus((Integer) map.get("status"));
        user.setHeadPortrait((String) map.get("headPortrait"));
        System.out.println(user);
        return JSONArray.toJSONString(new ResponseVo<>("null",user,"0x0006"));
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @Override
    public String register(User user) {
        if (user == null){
            return JSONArray.toJSONString(new ResponseVo<>("is null",null,"500"));
        }
        //校验学号还有用户名是否唯一
        User userInDB = userMapper.checkUsernameAndStudentId(user);
        if (userInDB != null){
            return JSONArray.toJSONString(new ResponseVo<>("用户名或者学号必须保证唯一",null,"500"));
        }

        //写入mysql
        user.setCreateTime(new Date());
        Long aLong = userMapper.register(user);
        if (aLong <= 0){
            return JSONArray.toJSONString(new ResponseVo<>("error",null,"500"));
        }

        return JSONArray.toJSONString(new ResponseVo<>("注册成功",null,"200"));
    }
}
