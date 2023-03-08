package com.dazuizui.business.controller;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.bo.QueryContestSubmissionLogBo;
import com.dazuizui.business.service.onlineJudge.AcContestQuestionSerivce;
import com.dazuizui.business.util.ThreadLocalUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@Api(value = "比赛提交记录",tags = {"比赛提交记录"})
@RequestMapping("/AcContestQuestion")
@RestController
public class AcContestQuestionController {
    @Autowired
    private AcContestQuestionSerivce acContestQuestionSerivce;
    /**
     * 查看本题提交日志
     * @return
     */
    @ApiOperation("查看提交日志")
    @PostMapping("/queryContestSubmissionLog")
    public String queryContestSubmissionLog(@RequestBody QueryContestSubmissionLogBo queryContestSubmissionLogBo){
        //身份验证过期和权限鉴别
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        if (map.get("error") != null){
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }
        return acContestQuestionSerivce.queryContestSubmissionLog(queryContestSubmissionLogBo);
    }
}
