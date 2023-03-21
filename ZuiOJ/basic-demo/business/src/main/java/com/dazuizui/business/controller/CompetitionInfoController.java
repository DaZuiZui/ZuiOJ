package com.dazuizui.business.controller;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.bo.*;
import com.dazuizui.business.service.onlineJudge.CompetitionInfoService;
import com.dazuizui.business.util.ThreadLocalUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 参赛选手数据
 */
@CrossOrigin
@RestController
@RequestMapping("/CompetitionInfo")
@Api(value = "参赛选手数据",tags = {"参赛选手数据"})
public class CompetitionInfoController {

    @Autowired
    private CompetitionInfoService competitionInfoService;



    /**
     * 通过id删除比赛选手
     * @param deleteTheCompetitionByIdBo
     * @return
     */
    @ApiOperation("通过id删除比赛选手")
    @PostMapping("/deleteTheCompetitionById")
    public String deleteTheCompetitionById(@RequestBody DeleteTheCompetitionByIdBo deleteTheCompetitionByIdBo){
        //身份验证过期 or 权限不足
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        if (map.get("error") != null){
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return competitionInfoService.deleteTheCompetitionById(deleteTheCompetitionByIdBo);
    }

    /**
     * 删除所有比赛选手通过比赛Id
     * @Param deleteAllCompetitionInfoByContestIdBo
     */
    @ApiOperation("删除所有比赛选手通过比赛Id")
    @PostMapping("/deleteAllCompetitionInfoByContestId")
    public String deleteAllCompetitionInfoByContestId(@RequestBody DeleteAllCompetitionInfoByContestIdBo deleteAllCompetitionInfoByContestIdBo){
        //身份验证过期 or 权限不足
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        if (map.get("error") != null){
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }
        return competitionInfoService.deleteAllCompetitionInfoByContestId(deleteAllCompetitionInfoByContestIdBo);
    }



    /**
     * 管理员插入比赛选手信息
     * @return
     */
    @ApiOperation("管理员插入比赛选手信息")
    @PostMapping("/adminAddCompetitionInfo")
    public String adminAddCompetitionInfo(@RequestBody AdminAddCompetitionInfoBo adminAddCompetitionInfoBo){
        //身份验证过期 or 权限不足 or 幂等性操作
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        if (map.get("error") != null){
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return competitionInfoService.adminAddCompetitionInfo(adminAddCompetitionInfoBo);
    }
    /**
     * 查看排名
     * @param contestId
     * @return
     */
    @ApiOperation("查看排名")
    @GetMapping("/viewranking")
    public String viewRanking(@RequestParam("contestId")Long contestId,@RequestParam("page")Integer page){

        return competitionInfoService.viewRanking(contestId,page-1,25);
    }

    /**
     * 分页获取比赛选手信息
     * @param paglingQueryContestantsInThisContestBo
     * @return
     */
    @ApiOperation("分页获取比赛选手信息")
    @PostMapping("/admin/paglingQueryContestantsInThisContest")
    public String paglingQueryContestantsInThisContest(@RequestBody PaglingQueryContestantsInThisContestBo paglingQueryContestantsInThisContestBo){
        return competitionInfoService.paglingQueryContestantsInThisContest(paglingQueryContestantsInThisContestBo);
    }
}
