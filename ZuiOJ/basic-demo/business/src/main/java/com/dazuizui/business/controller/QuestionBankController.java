package com.dazuizui.business.controller;

import com.dazuizui.basicapi.entry.bo.QuestionBankBo;
import com.dazuizui.business.service.onlineJudge.QuestionBankService;
import com.dazuizui.business.util.ThreadLocalUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/question")
public class QuestionBankController {

    @Autowired
    private QuestionBankService questionBankService;

    /**
     * 提交问题
     * @param Idemtoken
     * @param token
     * @param questionBankBo
     * @return
     */
    @ApiOperation("提交题目")
    @PostMapping("/postQuestion")
    public String postQuestion(@RequestParam("Idemtoken")String Idemtoken, @RequestParam("token")String token, @RequestBody QuestionBankBo questionBankBo){
        System.out.println("???");
         return questionBankService.postQuestion(questionBankBo);
     }


    /**
     * 分页获取题目
     * @param pages
     * @param number
     * @return
     */
     @ApiOperation("分页获取题库")
     @GetMapping("/pagingToGetQuestion")
     public String pagingToGetQuestion(@RequestParam("pages")int pages,@RequestParam("number")int number){
         return questionBankService.pagingToGetQuestion(pages,number);
     }

    /**
     * 根据id获取题目
     * @param token
     * @param id
     * @return
     */
    @ApiOperation("根据id获取题目")
    @GetMapping("/getQuestionById")
    public String getQuestionById(@RequestParam("token")String token,@RequestParam("id")Long id,@RequestParam("questionType") Integer questionType,@RequestParam("contestId")Long contestId){
         //System.err.println(ThreadLocalUtil.mapThreadLocalOfJWT.get());
        System.out.println(contestId);
        return questionBankService.getQuestionById(id, questionType);
    }
}
