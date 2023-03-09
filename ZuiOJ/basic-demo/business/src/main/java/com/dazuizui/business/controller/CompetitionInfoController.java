package com.dazuizui.business.controller;

import com.dazuizui.business.domain.bo.PaglingQueryContestantsInThisContestBo;
import com.dazuizui.business.service.onlineJudge.CompetitionInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * 查看排名
     * @param contestId
     * @return
     */
    @ApiOperation("查看排名")
    @GetMapping("/viewranking")
    public String viewRanking(@RequestParam("contestId")Long contestId,@RequestParam("page")Integer page){
        System.out.println(page+"c"+contestId);
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
