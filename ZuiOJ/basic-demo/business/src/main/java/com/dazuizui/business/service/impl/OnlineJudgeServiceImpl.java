package com.dazuizui.business.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.bo.ProgramBo;
import com.dazuizui.business.mapper.LanguageCommandMapper;
import com.dazuizui.business.service.OnlineJudgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        List<String> strings = languageCommandMapper.selectCommandByLanguageIdAndStatus(programBo.getLanguageId(), 0);
        System.out.println(strings);
        return JSONArray.toJSONString("AC");
    }


}
