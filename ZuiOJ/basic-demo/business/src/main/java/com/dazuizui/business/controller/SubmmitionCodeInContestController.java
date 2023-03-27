package com.dazuizui.business.controller;

import com.dazuizui.business.domain.bo.FilterQueryMatchSaveCodeBo;
import com.dazuizui.business.service.onlineJudge.SubmmitionCodeInContestSerivce;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 比赛时提交代码的存储控制器
 */
@CrossOrigin
@RequestMapping("/SubmmitionCodeInContestController")
@RestController
@Api(value = "比赛时提交代码的存储控制器",tags = {"比赛时提交代码的存储控制器"})
public class SubmmitionCodeInContestController {
    @Autowired
    private SubmmitionCodeInContestSerivce submmitionCodeInContestSerivce;

    /**
     * 筛选查询比赛提交保存的代码
     * @param findByContestIdAndQuestionIdAndUserIdBo
     * @return
     */
    @ApiOperation("筛选查询比赛提交保存的代码")
    @PostMapping("/filterQueryMatchSaveCode")
    public String filterQueryMatchSaveCode(@RequestBody FilterQueryMatchSaveCodeBo findByContestIdAndQuestionIdAndUserIdBo){
        return submmitionCodeInContestSerivce.filterQueryMatchSaveCode(findByContestIdAndQuestionIdAndUserIdBo);
    }


}
