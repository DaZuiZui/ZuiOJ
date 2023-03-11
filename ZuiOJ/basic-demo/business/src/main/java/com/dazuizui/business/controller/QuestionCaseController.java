package com.dazuizui.business.controller;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.bo.AddQuestionCaseBo;
import com.dazuizui.basicapi.entry.bo.AdminQueryQuestionCaseBo;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.bo.UpdateQuestionCaseBo;
import com.dazuizui.business.service.onlineJudge.QuestionCaseSerivce;
import com.dazuizui.business.util.ThreadLocalUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 案例控制器管理
 */
@CrossOrigin
@RestController
@RequestMapping("/questionCase")
public class QuestionCaseController {

    @Autowired
    private QuestionCaseSerivce questionCaseSerivce;

    /**
     * 通过案例id删除案例
     * @param id            案例id
     * @param questionId    问题id
     * @return
     */
    @ApiOperation("通过案例id删除案例")
    @PostMapping("/deleteCaseByCaseId")
    public String deleteCaseByCaseId(@RequestParam("token") String token,@RequestParam("id") Long id,@RequestParam("questionId") Long questionId){
        return questionCaseSerivce.deleteCaseByCaseId(id,questionId);
    }

    /**
     * 修改案例
     * @param updateQuestionCaseBo
     * @return
     */
    @ApiOperation("修改案例")
    @PostMapping("/UpdateQuestionCase")
    public String updateQuestionCase(@RequestBody UpdateQuestionCaseBo updateQuestionCaseBo){
        System.out.println(updateQuestionCaseBo);
        //身份验证过期或者权限不足
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        if (map.get("error") != null){
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return questionCaseSerivce.updateQuestionCase(updateQuestionCaseBo);
    }

    /**
     * 添加问题案例
     * @param addQuestionCase
     * @return
     */
    @ApiOperation("添加问题案例")
    @PostMapping("/addQuestionCase")
    public String addQuestionCase(@RequestBody AddQuestionCaseBo addQuestionCase){
        return questionCaseSerivce.addQuestionCase(addQuestionCase);
    }

    /**
     * 管理员查询案例通过id
     * @param adminQueryQuestionCaseBo 实体，内包含用户token 和 id
     * @return QuestionCasePagingDateVO
     *      内涵当前题号的数据量和本分页区间的数据
     */
    @ApiOperation("分页查询案例")
    @PostMapping("/getDateByPaging")
    public String getAllCaseOfQuestionById(@RequestBody AdminQueryQuestionCaseBo adminQueryQuestionCaseBo){
        return questionCaseSerivce.queryTheQuestionCasesByQuestionId(adminQueryQuestionCaseBo);
    }
}
