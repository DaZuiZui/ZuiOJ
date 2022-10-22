package com.dazuizui.business.service.impl;


import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.dazuizui.basicapi.InitializerData;
import com.dazuizui.basicapi.entry.ProblemLimit;
import com.dazuizui.basicapi.entry.QuestionCase;
import com.dazuizui.basicapi.entry.bo.ProgramBo;
import com.dazuizui.business.mapper.LanguageCommandMapper;
import com.dazuizui.business.mapper.ProblemLimitMapper;
import com.dazuizui.business.mapper.QuestionCaseMapper;
import com.dazuizui.business.service.OnlineJudgeService;
import com.dazuizui.business.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class OnlineJudgeServiceImpl implements OnlineJudgeService {
    @Autowired
    private LanguageCommandMapper languageCommandMapper;
    @Autowired
    private ProblemLimitMapper problemLimitMapper;
    @Autowired
    private QuestionCaseMapper questionCaseMapper;
    /**
     * 判决代码
     * @param programBo
     * @return
     */
    @Override
    public String judgeTheProgram(ProgramBo programBo){
        /**
         * 初始化代码运行还击那个
         */
        HashMap<Integer, List<String>> map = InitializerData.langSystem.get(programBo.getLanguageId());
        programBo.setEnv(Arrays.asList("PATH=/usr/bin:/bin"));
        programBo.setParseCodeArgs(map.get(new Integer(0)));
        programBo.setCopyOutCached(map.get(new Integer(2)));
        programBo.setRunCommandArgs(map.get(new Integer(1)));
        /**
         * 初始化题目限制
         */
        ProblemLimit problemLimit = problemLimitMapper.queryTheProblemLimitByQuestionId(programBo.getTopicId());
        programBo.setProblemLimit(problemLimit);
        /**
         * 获取案例
         */
        List<QuestionCase> questionCases = questionCaseMapper.queryTheQuestionCasesByQuestionId(programBo.getTopicId());

        //发起请求
        JSONObject request = HttpUtil.request(programBo);


        System.out.println("\n\n\n\nqwe"+request.get("status"));
        return "AC";
    }


}
