package com.dazuizui.business.controller;

import com.dazuizui.business.service.student.CollegeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 学院板块控制器
 */
@CrossOrigin
@RequestMapping("/college")
@RestController
@Api(value = "学院控制器",tags = {"学院控制器"})
public class CollegeController {

    //学院业务
    @Autowired
    private CollegeService collegeService;

    /**
     * @author 杨易达
     *  查看所有学院，数据极少，暂不进行优化
     * @param id 大学id，IdOfUniversty
     * @return
     */
    @GetMapping("/getALlCollegeByUniverstyId")
    @ApiOperation("获取全部学院")
    public String getALlCollegebyUniverstyId(@RequestParam("id") Long id){
        System.err.println(id+"as");
        return collegeService.getALlCollegebyUniverstyId(id);
    }
}
