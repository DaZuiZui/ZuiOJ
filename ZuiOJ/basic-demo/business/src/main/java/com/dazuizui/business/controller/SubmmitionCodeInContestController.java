package com.dazuizui.business.controller;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.CodeInContest;
import com.dazuizui.business.domain.bo.DuplicateCodeBo;
import com.dazuizui.business.domain.bo.FilterQueryMatchSaveCodeBo;
import com.dazuizui.business.mongodao.SubmmitionCodeInContestRepository;
import com.dazuizui.business.service.onlineJudge.SubmmitionCodeInContestSerivce;
import com.dazuizui.business.util.ThreadLocalUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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


    @Autowired
    private MongoTemplate mongoTemplate;

    /***
     * @author Bryan Yang 2023 11 12
     * @param contestId
     * @return
     */
    @ApiOperation("测试")
    @PostMapping("/findStatusCountsByContestId")
    public String findStatusCountsByContestId(@RequestParam("contestId")Long contestId){

//        return JSONArray.toJSONString(submmitionCodeInContestSerivce.findStatusCountsByContestId(contestId));
        return null;
    }



    /**
     * 获取涉嫌重复的代码
     * @param duplicateCode
     * @return
     */
    @ApiOperation("获取涉嫌查重的代码")
    @PostMapping("/getDuplicateCode")
    public String getDuplicateCode(@RequestBody DuplicateCodeBo duplicateCode){
        //身份验证过期和权限鉴别
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        ThreadLocalUtil.mapThreadLocal.remove();
        if ( map.get("error") != null) {
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return submmitionCodeInContestSerivce.getDuplicateCode(duplicateCode);
    }

    /**
     * 通过id查询代码详细信息
     */
    @ApiOperation("通过id查询代码详细信息")
    @PostMapping("/findOneById")
    public String findOneById(@RequestParam("token")String token,@RequestParam("id")Long id){
        //身份验证过期和权限鉴别
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        ThreadLocalUtil.mapThreadLocal.remove();
        if ( map.get("error") != null) {
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }


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
        ThreadLocalUtil.mapThreadLocal.remove();
        if ( map.get("error") != null) {
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }


        return submmitionCodeInContestSerivce.filterQueryMatchSaveCode(findByContestIdAndQuestionIdAndUserIdBo);
    }


    /**
     * 通过比赛id删除提交数据
     * @param token
     * @param contestId
     * @return
     */
    public String deleteByContestId(@RequestParam("token")String token,@RequestParam("contestId")Long contestId){
        return "";
    }

    /**
     * 通过提交记录id删除记录
     * @param id  mongoDb主键id
     * @param token token
     * @return
     */
    @ApiOperation("通过提交记录id删除记录")
    @PostMapping("/deleteById")
    public String deleteById(@RequestParam("token")String token,@RequestParam("id")String id){
        //身份验证过期和权限鉴别
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        ThreadLocalUtil.mapThreadLocal.remove();
        if ( map.get("error") != null) {
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return submmitionCodeInContestSerivce.deleteById(id);
    }

}
