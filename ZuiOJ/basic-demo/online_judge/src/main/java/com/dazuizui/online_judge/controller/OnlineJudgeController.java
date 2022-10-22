package com.dazuizui.online_judge.controller;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.bo.ProgramBo;
import org.springframework.web.bind.annotation.*;

/**
 * @author Bryan yang(杨易达)
 * 在线判题系统控制器
 */
@RequestMapping("/sandbox")
@RestController
public class OnlineJudgeController {
    @PostMapping("/judge")
    public String postByObject(@RequestBody ProgramBo beanRequest) {

        return JSONArray.toJSONString(beanRequest);
    }

}
