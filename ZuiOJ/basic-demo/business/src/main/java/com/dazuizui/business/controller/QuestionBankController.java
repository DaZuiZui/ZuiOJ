package com.dazuizui.business.controller;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.StatusCodeMessage;
import com.dazuizui.basicapi.entry.bo.DeleteQuestion;
import com.dazuizui.basicapi.entry.bo.PostQuestionBo;
import com.dazuizui.basicapi.entry.bo.QuestionBankBo;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.bo.BatchPhysicalDeleteQuestionsBo;
import com.dazuizui.business.domain.bo.PagingToGetQuestionBankListByStatusAndDelFlagBo;
import com.dazuizui.business.domain.bo.UpdateQuestionAndLimitByQuestionIdBo;
import com.dazuizui.business.service.onlineJudge.QuestionBankService;
import com.dazuizui.business.util.ThreadLocalUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/question")
@Api(value = "题库控制器",tags = {"题库控制器"})
public class QuestionBankController {

    @Autowired
    private QuestionBankService questionBankService;

    /**
     * 修改题目限制和题目
     */
    @ApiOperation("修改题目限制和题目")
    @PostMapping("/updateQuestionAndLimitByQuestionId")
    public String updateQuestionAndLimitByQuestionId(@RequestBody UpdateQuestionAndLimitByQuestionIdBo questionAndLimitByQuestionIdBo){
        //身份验证过期或者权限不足
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        ThreadLocalUtil.mapThreadLocal.remove();
        //报错排查
        if ( map.get("error") != null) {
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return questionBankService.updateQuestionAndLimitByQuestionId(questionAndLimitByQuestionIdBo);
    }

    /**
     * 根据id获取题目
     * @param token
     * @param id
     * @return
     */
    @ApiOperation("根据id获取题目")
    @PostMapping("/adminGetQuestionById")
    public String adminGetQuestionById(@RequestParam("token")String token,@RequestParam("id")Long id){
        //身份验证过期或者权限不足
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        ThreadLocalUtil.mapThreadLocal.remove();
        //报错排查
        if ( map.get("error") != null) {
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return questionBankService.adminGetQuestionById(id);
    }

    /**
     * 逻辑删除问题
     *    同时也会删除案例
     * @param deleteQuestion
     * @return
     */
    @ApiOperation("逻辑删除问题-同时也会删除所以案例")
    @PostMapping("/admin/del")
    public String deleteQuestionById(@RequestBody DeleteQuestion deleteQuestion){
        //System.err.println("asdsa"+deleteQuestion);
        //查看aop前置鉴权是否有问题
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        ThreadLocalUtil.mapThreadLocal.remove();
        //报错排查
        if ( map.get("error") != null) {
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
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
        ThreadLocalUtil.mapThreadLocal.remove();
        //报错排查
        if ( map.get("error") != null) {
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }


        return questionBankService.pagingToGetQuestionOfAdmin(pages,number);
    }

    /**
     * 提交问题 out
     * @param Idemtoken
     * @param token
     * @param postQuestionBo
     * @return
     */
    @ApiOperation("提交题目和限制")
    @PostMapping("/postQuestion")
    public String postQuestion(@RequestParam("Idemtoken")String Idemtoken, @RequestParam("token")String token,@RequestBody PostQuestionBo postQuestionBo){
        //查看aop前置环绕是否出现问题
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        ThreadLocalUtil.mapThreadLocal.remove();
        //报错排查
        if ( map.get("error") != null) {
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }
        //非空判断
        System.out.println(postQuestionBo);
        if(postQuestionBo == null || postQuestionBo.getQuestionBankBo() == null || postQuestionBo.getProblemLimit() == null){
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.IsNull,null, StatusCode.IsNull));
        }

        return questionBankService.postQuestionAndLimit(postQuestionBo);
    }

    /**
     * 提交问题 out
     * @param Idemtoken
     * @param token
     * @param questionBankBo
     * @return
     */
    @ApiOperation("提交题目淘汰")
    @PostMapping("/postQuestionold")
    @Transactional
    public String postQuestionold(@RequestParam("Idemtoken")String Idemtoken, @RequestParam("token")String token, @RequestBody QuestionBankBo questionBankBo){
         return questionBankService.postQuestionOld(questionBankBo);
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

        return questionBankService.getQuestionById(id, questionType);
    }

    /**
     * 在比赛的时候根据id获取题目
     * @param token
     * @param id
     * @return
     */
    @ApiOperation("根据id获取题目")
    @GetMapping("/getQuestionByIdDuringContest")
    public String getQuestionByIdDuringContest(@RequestParam("token")String token,@RequestParam("id")Long id,@RequestParam("questionType") Integer questionType,@RequestParam("contestId")Long contestId){
        return "";
    }


    /**
     * 分页获取题库通过status and delflag
     * @param pagingToGetQuestionBankListByStatusAndDelFlagBo
     * @return
     */
    @ApiOperation("分页获取题库通过status and delflag")
    @PostMapping("/admin/pagingToGetQuestionBankListByStatusAndDelFlag")
    public String pagingToGetQuestionBankListByStatusAndDelFlag(@RequestBody PagingToGetQuestionBankListByStatusAndDelFlagBo pagingToGetQuestionBankListByStatusAndDelFlagBo){

        //查看aop前置环绕是否出现问题
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();

        //报错排查
        if ( map.get("error") != null) {
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return questionBankService.pagingToGetQuestionBankListByStatusAndDelFlag(pagingToGetQuestionBankListByStatusAndDelFlagBo);
    }

    /**
     * 批量物理删除题库
     * @return
     */
    @ApiOperation("批量物理删除题目")
    @PostMapping("/admin/batchPhysicalDeleteQuestions")
    public String batchDeleteQuestions(@RequestBody BatchPhysicalDeleteQuestionsBo batchPhysicalDeleteQuestionsBo){
        //查看aop前置环绕是否出现问题
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();

        //报错排查
        if ( map.get("error") != null) {
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return questionBankService.batchDeleteQuestions(batchPhysicalDeleteQuestionsBo.getQuestionList());
    }
}
