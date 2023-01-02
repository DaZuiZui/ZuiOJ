package com.dazuizui.business.controller;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.Major;
import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.service.student.MajorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 杨易达
 * 大学专业控制器
 * universty major controller
 */
@CrossOrigin
@RestController
@RequestMapping("/major")
@Api(value = "major",tags = {"major"})
public class MajorController {

    @Autowired
    private MajorService majorService;

    /**
     * 通过学院id查询所有专业
     *  目前没有做缓存层优化，因为数据量极少
     *  There is currenly no cahing layer opimization,because the amount of date is very small
     * @param id    学院ID
     * @return
     */
    @ApiOperation("获取专业通过id")
    @GetMapping("/getAllMajorByCollegeId")
    public String getAllMajorByCollegeId(@Param("id")Long id){
        //非空判断
        if (id == null){
            return JSONArray.toJSONString(new ResponseVo<>("非空判断",null, StatusCode.IsNull));
        }

       return majorService.getAllMajorByCollegeId(id);
    }

}
