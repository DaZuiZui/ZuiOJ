package com.dazuizui.business.service.student.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.StudentCertification;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.mapper.student.StudentMapper;
import com.dazuizui.business.service.student.StudentService;
import com.dazuizui.business.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * 学生业务接口实现
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    /**
     * 学生认证
     * @param studentCertification
     * @return
     */
    @Override
    public String studentCertification(StudentCertification studentCertification){
        /**
         * todo 查看该学号是否被占用
         */
        //设置操作者id
        Map<String, Object> userinfo = ThreadLocalUtil.mapThreadLocalOfJWT.get().get("userinfo");
        studentCertification.setUserId(Long.valueOf(String.valueOf(userinfo.get("id"))));
        studentCertification.setCreateTime(new Date());

        /**
         * 添加认证
         */
        Long aLong = studentMapper.studentCertification(studentCertification);
        if (aLong <= 0){
            return JSONArray.toJSONString(new ResponseVo<>("操作失败",null, StatusCode.Error));
        }

        /**
         * 考虑是否要单独一个表来记录相互的索引 todo
         */
        //获取新增加的数据的主键
        Long authenticationId = studentCertification.getId();
        Long userId           = studentCertification.getUserId();

        return JSONArray.toJSONString(new ResponseVo<>("认证成功",null, StatusCode.OK));
    }
}
