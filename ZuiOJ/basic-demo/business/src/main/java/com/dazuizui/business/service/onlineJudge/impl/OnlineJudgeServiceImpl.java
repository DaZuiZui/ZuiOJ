package com.dazuizui.business.service.onlineJudge.impl;

import cn.hutool.json.JSONObject;
import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.InitializerData;
import com.dazuizui.basicapi.entry.AcContestQuestion;
import com.dazuizui.basicapi.entry.ProblemLimit;
import com.dazuizui.basicapi.entry.QuestionCase;
import com.dazuizui.basicapi.entry.bo.ProgramBo;
import com.dazuizui.business.mapper.AcContestQuestionMapper;
import com.dazuizui.business.mapper.LanguageCommandMapper;
import com.dazuizui.business.mapper.ProblemLimitMapper;
import com.dazuizui.business.mapper.QuestionCaseMapper;
import com.dazuizui.business.service.onlineJudge.AcContestQuestionSerivce;
import com.dazuizui.business.service.onlineJudge.OnlineJudgeService;
import com.dazuizui.business.util.HttpUtil;
import com.dazuizui.business.util.RedisUtil;
import com.dazuizui.business.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private AcContestQuestionSerivce acContestQuestionSerivce;


    /**
     * 判决代码
     * 不要动 危险
     *                              -- Bryan yang
     * @param programBo
     * @return
     */
    @Override
    @Transactional
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
        List<QuestionCase> questionCases = redisUtil.getListInRedis("ZuiOJ:QuestionBack:QuestionID:"+programBo.getTopicId());
        System.out.println(questionCases.size());
        if (questionCases.size() == 0){
            questionCases = questionCaseMapper.queryTheQuestionCasesByQuestionId(programBo.getTopicId());
            //写入redis
            redisUtil.putListInRedis("ZuiOJ:QuestionBack:QuestionID:"+programBo.getTopicId(),60*60*24*15,questionCases);
            System.out.println("in db");
        }


        JSONObject request = new JSONObject();
        for (QuestionCase questionCase : questionCases) {
            programBo.setInput(questionCase.getInputs());
            //发起请求
            request = HttpUtil.request(programBo);

            if (!request.get("status").equals("Accepted")) {
               break;
            }

            //判断答案是否正确
            JSONObject jsonObject1 = new JSONObject(request.get("files"));
            String stdout = jsonObject1.get("stdout").toString() ;
            stdout =  stdout.replace("\n","\\n").trim();
            System.out.println(questionCase.getAnswer()+"and"+stdout);
            if (!stdout.equals(questionCase.getAnswer())) {
                request.set("status","Answer error");
                break;
            }
        }

        //如果通过并且是比赛类型的题目
        if (programBo.getQuestionType() != 1 ) {
            //查看是否为比赛题目
            String JWTStringID = (String) ThreadLocalUtil.mapThreadLocalOfJWT.get().get("userinfo").get("id");
            Long id = Long.valueOf(JWTStringID);
            AcContestQuestion acContestQuestion = new AcContestQuestion();
            acContestQuestion.setContestId(programBo.getContestId());
            acContestQuestion.setUserId(id);
            acContestQuestion.setQuestionId(programBo.getTopicId());
            //在比赛中标记记录
            acContestQuestionSerivce.submitAnswer(acContestQuestion, (String) request.get("status"));
            //写入日记
        }

        System.err.println(request.get("status"));
        /**
         * 日志记录用户的状态
         */
        return JSONArray.toJSONString(request);
    }


}
