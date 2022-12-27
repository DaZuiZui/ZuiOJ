package com.dazuizui.business.controller;

import com.dazuizui.basicapi.entry.bo.AdminQueryQuestionCaseBo;
import com.dazuizui.basicapi.entry.vo.QuestionCasePagingDateVo;
import com.dazuizui.business.service.onlineJudge.QuestionCaseSerivce;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
