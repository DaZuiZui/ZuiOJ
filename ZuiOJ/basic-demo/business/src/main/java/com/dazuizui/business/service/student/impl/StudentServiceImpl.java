package com.dazuizui.business.service.student.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.*;
import com.dazuizui.basicapi.entry.vo.GetStudentInfoVo;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.mapper.student.*;
import com.dazuizui.business.service.student.StudentService;
import com.dazuizui.business.util.RedisUtil;
import com.dazuizui.business.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

/**
 * 学生业务接口实现
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private UniverstyMapper universtyMapper;
    @Autowired
    private CollegeMapper collegeMapper;
    @Autowired
    private MajorMapper majorMapper;
    @Autowired
    private LessonMapper lessonMapper;
    @Autowired
    private RedisUtil redisUtil;

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
         * 写入redis
         */

        /**
         * 考虑是否要单独一个表来记录相互的索引 todo
         */
        //获取新增加的数据的主键
        Long authenticationId = studentCertification.getId();
        Long userId           = studentCertification.getUserId();

        return JSONArray.toJSONString(new ResponseVo<>("认证成功",null, StatusCode.OK));
    }

    /**
     * 获取学生认证信息通过id
     */
    @Override
    @Transactional
    public String getStudentInfo() {
        Map<String, Object> userinfo = ThreadLocalUtil.mapThreadLocalOfJWT.get().get("userinfo");
        Long id = new Long(Long.valueOf(String.valueOf(userinfo.get("id"))));

        //去redis查询该数据是否存在
        GetStudentInfoVo student = (GetStudentInfoVo) redisUtil.getStringInRedis(RedisKey.ZuiOjStudentCertificationUserId + id);
        if (student == null){
            //去数据库查询
            student = this.queryStudentByUserId(id);
            if (student == null){
                return JSONArray.toJSONString(new ResponseVo<>("操作失败",null, StatusCode.Error));
            }
            //写入redis
            redisUtil.setStringInRedis(RedisKey.ZuiOjStudentCertificationUserId+id,RedisKey.OutTime,student);
        }

        return JSONArray.toJSONString(new ResponseVo<>("查询成功",student, StatusCode.OK));
    }

    /**
     * 通过认证主键获取学生信息
     */
    @Override
    @Transactional
    public GetStudentInfoVo queryStudentByUserId(Long userId) {
        GetStudentInfoVo res = new GetStudentInfoVo();
        //获取学生认证信息
        StudentCertification studentCertification = studentMapper.queryStudentInfoByUserId(userId);
        res.setStudentId(studentCertification.getStudentId());
        res.setIc(studentCertification.getIc());
        res.setIcType(studentCertification.getIcType());
        res.setName(studentCertification.getName());
        res.setCreateTime(studentCertification.getCreateTime());
        res.setId(studentCertification.getId());
        //解析学生学校
        Universty universty = universtyMapper.queryUniverstryByUniverstyId(studentCertification.getUniversty());
        res.setUniversty(universty);
        //解析学生学院
        College college = collegeMapper.queryCollegeByCollegeId(studentCertification.getCollege());
        res.setCollege(college);
        //解析学生专业
        Major major = majorMapper.queryMajorByMajorId(studentCertification.getCollege());
        res.setMajor(major);
        //解析班级
        Lesson lesson = lessonMapper.queryLessonByLessonId(studentCertification.getLesson());
        res.setLesson(lesson);
        return res;
    }

    /**
     * 修改学生信息
     * @param studentCertificationBo
     * @return
     */
    @Override
    @Transactional
    public String updateStudentCertification(StudentCertification studentCertificationBo) {
        Map<String, Object> userinfo = ThreadLocalUtil.mapThreadLocalOfJWT.get().get("userinfo");
        Long userId = new Long(Long.valueOf(String.valueOf(userinfo.get("id"))));
        studentCertificationBo.setUserId(userId);
        //修改mysql
        Long aLong = studentMapper.updateStudentCertification(studentCertificationBo);
        if (aLong == 0){
            //System.err.println(studentCertificationBo);
            //System.out.println("??");
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
        }
        //修改redis
        GetStudentInfoVo getStudentInfoVo = this.queryStudentByUserId(userId);
        redisUtil.setStringInRedis(RedisKey.ZuiOjStudentCertificationUserId+userId,RedisKey.OutTime,getStudentInfoVo);

        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,null, StatusCode.OK));
    }
}
