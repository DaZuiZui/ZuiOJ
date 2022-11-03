package com.dazuizui.business.controller;

import com.dazuizui.basicapi.entry.bo.QuestionBankBo;
import com.dazuizui.business.service.onlineJudge.QuestionBankService;
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
    @PostMapping("/postQuestion")
    public String postQuestion(@RequestParam("Idemtoken")String Idemtoken, @RequestParam("token")String token, @RequestBody QuestionBankBo questionBankBo){
        System.out.println("???");
         return questionBankService.postQuestion(questionBankBo);
     }
}
