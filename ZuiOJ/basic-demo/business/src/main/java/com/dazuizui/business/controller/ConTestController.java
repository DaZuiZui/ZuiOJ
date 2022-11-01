package com.dazuizui.business.controller;

import com.dazuizui.basicapi.entry.Contest;
import com.dazuizui.business.service.onlineJudge.ConTestSerivce;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 竞赛接口
 */

@CrossOrigin
@RestController
@RequestMapping("/contest")
@Api(value = "比赛模板控制器",tags = {"比赛模板控制器"})
public class ConTestController {
    @Autowired
    private ConTestSerivce conTestSerivce;

    /**
     * 提交比赛
     * @param conTest
     * @return
     */
    @ApiOperation("提交比赛")
    @PostMapping("/postContest")
    public String postContest(@RequestParam("Idemtoken")String Idemtoken,@RequestParam("token")String token,@RequestBody Contest conTest){
        return conTestSerivce.postContest(conTest);
    }
}
