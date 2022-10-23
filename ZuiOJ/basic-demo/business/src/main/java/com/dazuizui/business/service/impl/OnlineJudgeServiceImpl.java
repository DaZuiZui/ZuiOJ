package com.dazuizui.business.service.impl;

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
         * 获取案例he
         */
        JSONObject request = new JSONObject();
        List<QuestionCase> questionCases = questionCaseMapper.queryTheQuestionCasesByQuestionId(programBo.getTopicId());

        for (QuestionCase questionCase : questionCases) {
            programBo.setInput(questionCase.getInputs());
            //发起请求
            request = HttpUtil.request(programBo);

            if (!request.get("status").equals("Accepted")) {
                break;
            }
            //判断答案是否正确
            JSONObject jsonObject1 = new JSONObject(request.get("files"));
            String stdout = jsonObject1.get("stdout").toString().trim();
            System.out.println(questionCase.getAnswer()+"and"+stdout);
            if (!stdout.equals(questionCase.getAnswer())) {
                return "Anwser Error";
            }
        }

        /**
         * 日志记录用户的状态
         */

        return (String) request.get("status");
    }


}
