package com.dazuizui.business.controller;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.RedisKey;
import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.StudentCertification;
import com.dazuizui.basicapi.entry.bo.StudentCertificationBo;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.service.student.StudentService;
import com.dazuizui.business.util.ThreadLocalUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 学生板块控制器
 */
@CrossOrigin
@RequestMapping("/student")
@RestController
@Api(value = "学生板块控制器",tags = {"学生板块控制器"})
public class StudentController {
    @Autowired
    private StudentService studentService;


    /**
     * 修改学生认证
     * @param studentCertificationBo
     * @return
     */
    @ApiOperation("修改学生认证")
    @PostMapping("/updatestudentCertification")
    public String updateStudentCertification(@RequestBody StudentCertificationBo studentCertificationBo){
        //查看aop前置环绕是否出现问题
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        if (map.get("error") != null){
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }
        if(studentCertificationBo == null){
            return JSONArray.toJSONString(new ResponseVo<>("服务器接收数据为null",null, StatusCode.IsNull));
        }

        return studentService.updateStudentCertification(studentCertificationBo.getStudentCertification());
    }

    /**
     * 学生认证
     * @param studentCertificationBo
     * @return
     */
    @ApiOperation("学生认证")
    @PostMapping("/studentCertification")
    public String studentCertification(@RequestBody StudentCertificationBo studentCertificationBo){
        //查看aop前置环绕是否出现问题
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        if (map.get("error") != null){
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }
        //业务操作
        return studentService.studentCertification(studentCertificationBo.getStudentCertification());
    }

    /**
     * 获取学生认证信息
     */
    @ApiOperation("获取学生认证信息")
    @GetMapping("/getStudentInfo")
    public String getStudentInfo(@RequestParam("token")String token){
        //查看aop前置环绕是否出现问题
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        System.out.println(map);
        if (map.get("error") != null){
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return studentService.getStudentInfo();
    }

}
