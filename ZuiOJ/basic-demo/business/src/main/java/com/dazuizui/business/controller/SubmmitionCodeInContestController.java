package com.dazuizui.business.controller;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.bo.FilterQueryMatchSaveCodeBo;
import com.dazuizui.business.service.onlineJudge.SubmmitionCodeInContestSerivce;
import com.dazuizui.business.util.ThreadLocalUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
     * 通过id查询代码详细信息
     */
    @ApiOperation("通过id查询代码详细信息")
    @PostMapping("/findOneById")
    public String findOneById(@RequestParam("token")String toekn,@RequestParam("id")Long id){
        return submmitionCodeInContestSerivce.findOneById(id);
    }

    /**
     * 筛选查询比赛提交保存的代码
     * @param findByContestIdAndQuestionIdAndUserIdBo
     * @return
     */
    @ApiOperation("筛选查询比赛提交保存的代码")
    @PostMapping("/filterQueryMatchSaveCode")
    public String filterQueryMatchSaveCode(@RequestBody FilterQueryMatchSaveCodeBo findByContestIdAndQuestionIdAndUserIdBo){
        //身份验证过期和权限鉴别
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        if (map.get("error") != null){
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return submmitionCodeInContestSerivce.filterQueryMatchSaveCode(findByContestIdAndQuestionIdAndUserIdBo);
    }

}
