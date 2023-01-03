package com.dazuizui.business.controller;

import com.alibaba.fastjson2.JSONArray;
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
     * 学生认证
     * @param studentCertificationBo
     * @return
     */
    @ApiOperation("学生认证")
    @PostMapping("/studentCertification")
    public String studentCertification(@RequestBody StudentCertificationBo studentCertificationBo){
        System.err.println("asd"+studentCertificationBo);
        //查看aop前置环绕是否出现问题
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        if (map.get("error") != null){
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }
        //业务操作
        return studentService.studentCertification(studentCertificationBo.getStudentCertification());
    }
}
