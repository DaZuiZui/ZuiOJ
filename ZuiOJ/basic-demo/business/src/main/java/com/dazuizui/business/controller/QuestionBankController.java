package com.dazuizui.business.controller;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.bo.DeleteQuestion;
import com.dazuizui.basicapi.entry.bo.QuestionBankBo;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.service.onlineJudge.QuestionBankService;
import com.dazuizui.business.util.RedisUtil;
import com.dazuizui.business.util.ThreadLocalUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/question")
public class QuestionBankController {

    @Autowired
    private QuestionBankService questionBankService;

    /**
     * 删除问题
     *    同时也会删除案例
     * @param deleteQuestion
     * @return
     */
    @ApiOperation("删除问题-同时也会删除所以案例")
    @PostMapping("/admin/del")
    public String deleteQuestionById(@RequestBody DeleteQuestion deleteQuestion){
        //System.err.println("asdsa"+deleteQuestion);
        //查看aop前置鉴权是否有问题
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        //System.err.println(map.get("error"));
        if (map.get("error") != null){
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,"0x5001"));
        }

        return questionBankService.deleteQuestionById(deleteQuestion.getId(), deleteQuestion.getQuestionType());
    }

    /**
     * 用户分页查找数据
     * @param pages   起始行数
     * @param number  一次查询多少个
     * @return
     */
    @ApiOperation("管理员查询")
    @GetMapping("/admin/pagingToGetQuestion")
    public String pagingToGetQuestionOfAdmin(@RequestParam("token")String token,@RequestParam("pages") int pages, @RequestParam("number") int number){
        //查看aop前置鉴权是否有问题
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        //System.err.println(map.get("error"));
        if (map.get("error") != null){
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,"0x5001"));
        }

        return questionBankService.pagingToGetQuestionOfAdmin(pages,number);
    }

    /**
     * 提交问题
     * @param Idemtoken
     * @param token
     * @param questionBankBo
     * @return
     */
    @ApiOperation("提交题目")
    @PostMapping("/postQuestion")
    @Transactional
    public String postQuestion(@RequestParam("Idemtoken")String Idemtoken, @RequestParam("token")String token, @RequestBody QuestionBankBo questionBankBo){

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
