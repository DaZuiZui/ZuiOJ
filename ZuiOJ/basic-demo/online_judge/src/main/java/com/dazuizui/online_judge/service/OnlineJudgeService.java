package com.dazuizui.online_judge.service;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.dazuizui.basicapi.entry.bo.ProgramBo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Bryan yang(杨易达)
 * 在线判断业务接口
 */
@Service
public interface OnlineJudgeService {
    /**
     * 判决代码
     * @param programBo
     * @return
     */
    @PatchMapping("/judge")
    public JSONObject judgeTheProgram(@RequestBody ProgramBo programBo);
}
