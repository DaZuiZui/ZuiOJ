package com.dazuizui.business.controller;


import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.StatusCodeMessage;
import com.dazuizui.basicapi.entry.bo.GetQuestionAnswerByPageBo;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.service.onlineJudge.QuestionAnswerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/questionAnswer")
@RestController
@Api(value = "题解板块控制器",tags = {"题解板块控制器"})
public class QuestionAnswerController {
    @Autowired
    private QuestionAnswerService questionAnswerService;

    /**
     * 获取题解贡献者根据贡献者题解数量贡献排序
     * @return
     */
    @ApiOperation("获取题解贡献者")
    @GetMapping("/getSolutionContributors")
    public String getSolutionContributors(Long questionId){
        //非空判断
        if (questionId == null || questionId == 0){
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.IsNull,null, StatusCode.IsNull));
        }

        return questionAnswerService.getSolutionContributors(questionId);
    }

    /**
     * 分页获取题解
     * @param getQuestionAnswerByPageBo
     * @return
     */
    @ApiOperation("分页获取题解")
    @PostMapping("/getQuestionAnswerByPage")
    public String getQuestionAnswerByPage(@RequestBody GetQuestionAnswerByPageBo getQuestionAnswerByPageBo){
        return questionAnswerService.getQuestionAnswerByPage(getQuestionAnswerByPageBo);
    }
}
