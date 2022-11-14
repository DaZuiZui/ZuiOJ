package com.dazuizui.business.controller;

import com.dazuizui.business.service.onlineJudge.CompetitionInfoService;
import io.swagger.annotations.Api;
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
    @GetMapping("/viewranking")
    public String viewRanking(@RequestParam("contestId")Long contestId,@RequestParam("page")Integer page){
        System.out.println(page+"c"+contestId);
        return competitionInfoService.viewRanking(contestId,page-1,25);
    }
}
