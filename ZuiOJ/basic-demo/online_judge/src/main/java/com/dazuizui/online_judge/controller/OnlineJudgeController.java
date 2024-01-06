package com.dazuizui.online_judge.controller;

import cn.hutool.json.JSONObject;
import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.bo.ProgramBo;
import com.dazuizui.online_judge.service.OnlineJudgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Bryan yang(杨易达)
 * 在线判题系统控制器
 */
@RequestMapping("/sandbox")
@RestController
public class OnlineJudgeController {
    @Autowired
    private OnlineJudgeService onlineJudgeService;



    /**
     * 判决代码
     * @param programBo
     * @return
     */
    @PostMapping("/judge")
    public String postByObject(@RequestBody ProgramBo programBo) {
        JSONObject jsonObject = onlineJudgeService.judgeTheProgram(programBo);
        return JSONArray.toJSONString(jsonObject);
    }

}
