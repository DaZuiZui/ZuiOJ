package com.dazuizui.online_judge.controller;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.bo.ProgramBo;
import org.springframework.web.bind.annotation.*;

/**
 * @author Bryan yang(杨易达)
 * 在线判题系统控制器
 */
@RequestMapping("/onlineJudge")
@RestController
public class OnlineJudgeController {
    /**
     * 判决代码
     * @param programBo
     * @return
     */
    @PatchMapping("/judge")
    public String judgeTheProgram(@RequestBody ProgramBo programBo){


        return JSONArray.toJSONString("ASD");
    }
}
