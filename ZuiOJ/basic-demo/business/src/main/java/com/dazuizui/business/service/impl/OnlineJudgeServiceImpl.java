package com.dazuizui.business.service.impl;


import cn.hutool.json.JSONArray;
import com.dazuizui.basicapi.InitializerData;
import com.dazuizui.basicapi.entry.bo.ProgramBo;
import com.dazuizui.business.mapper.LanguageCommandMapper;
import com.dazuizui.business.service.OnlineJudgeService;
import com.dazuizui.business.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class OnlineJudgeServiceImpl implements OnlineJudgeService {
    @Autowired
    private LanguageCommandMapper languageCommandMapper;

    /**
     * 判决代码
     * @param programBo
     * @return
     */
    @Override
    public String judgeTheProgram(ProgramBo programBo){
        System.out.println(InitializerData.langSystem);
        HashMap<Integer, List<String>> map = InitializerData.langSystem.get(programBo.getLanguageId());
        programBo.setParseCodeArgs(map.get(new Integer(0)));
        programBo.setRunCommandArgs(map.get(new Integer(2)));
        programBo.setEnv(map.get(new Integer(1)));
        System.out.println(com.alibaba.fastjson2.JSONArray.toJSONString(programBo));
        JSONArray request = HttpUtil.request(programBo);

        System.out.println(request);
        return "AC";
    }


}
