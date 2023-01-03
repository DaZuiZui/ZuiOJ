package com.dazuizui.business.controller;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.service.student.LessonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequestMapping("/lesson")
@RestController
@Api(tags = {"班级控制器"},value = "班级控制器")
public class LessonController {
    @Autowired
    private LessonService lessonService;

    /**
     * 通过专业id来查询班级，
     *      数据量极少暂不做优化
     * @param id
     * @return
     */
    @ApiOperation("通过专业id查询班级")
    @GetMapping("/ueryAllLessonByMajorId")
    public String ueryAllLessonByMajorId(@Param("id")Long id){
        //is null
        if (id == null || id == 0){
            return JSONArray.toJSONString(new ResponseVo<>("数据为null",null, StatusCode.IsNull));
        }

        return lessonService.ueryAllLessonByMajorId(id);
    }
}
