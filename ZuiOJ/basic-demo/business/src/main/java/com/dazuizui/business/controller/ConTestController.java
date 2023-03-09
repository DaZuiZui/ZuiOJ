package com.dazuizui.business.controller;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.CompetitionInfo;
import com.dazuizui.basicapi.entry.Contest;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.bo.AdminQueryGameInformationByPageBo;
import com.dazuizui.business.service.onlineJudge.ContestSerivce;
import com.dazuizui.business.util.ThreadLocalUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 竞赛接口
 */

@CrossOrigin
@RestController
@RequestMapping("/contest")
@Api(value = "比赛模板控制器",tags = {"比赛模板控制器"})
public class ConTestController {

    @Autowired
    private ContestSerivce conTestSerivce;


    /**
     * 移除比赛页面
     */
    @ApiOperation("移除比赛页面")
    @PostMapping("/admin/removeTheContestById")
    public String removeTheContestById(@RequestParam("token") String token,@RequestParam("id")Long id){
        return conTestSerivce.removeTheContestById(id);
    }

    /**
     * 修改比赛信息
     */
    @ApiOperation("修改比赛信息")
    @PostMapping("/updateContest")
    public String updateContest(@RequestParam("token")String token,@RequestBody Contest contest){
        //身份验证过期
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();

        if (map.get("error") != null){
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return conTestSerivce.updateContest(contest);
    }

    /**
     * 管理员分页查询比赛数据
     * @param adminQueryGameInformationByPageBo
     * @return
     */
    @PostMapping("/adminQueryGameInformationByPage")
    @ApiOperation("管理员分页获取数据")
    public String adminQueryGameInformationByPage(@RequestBody AdminQueryGameInformationByPageBo adminQueryGameInformationByPageBo){
        return conTestSerivce.adminQueryGameInformationByPage(adminQueryGameInformationByPageBo);
    }

    /**
     * 提交比赛
     * @param conTest
     * @return
     */
    @ApiOperation("提交比赛")
    @PostMapping("/postContest")
    public String postContest(@RequestParam("Idemtoken")String Idemtoken,@RequestParam("token")String token,@RequestBody Contest conTest){
        //身份验证过期
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        if (map.get("error") != null){
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return conTestSerivce.postContest(conTest);
    }

    /**
     * 获取未来赛制
     * @return
     */
    @ApiOperation("查询未开始的比赛或者赛制")
    @GetMapping("/futureEvents")
    public String getFutureEvents(){
        return conTestSerivce.getFutureEvents();
    }

    /**
     * 获取全部赛制
     * @return
     */
    @ApiOperation("查看全部赛事")
    @GetMapping("/allEvents")
    public String getAllEvents(){
        return conTestSerivce.getAllEvents();
    }

    /**
     * 通过id获取比赛赛事
     * @param id
     * @return
     */
    @ApiOperation("通过id获取比赛赛事")
    @GetMapping("/getEventById")
    public String getEventById(@RequestParam("toekn")String token ,@RequestParam("id")Long id){
        return conTestSerivce.getEventById(id);
    }

    /**
     * @author (Bryan yang)杨易达
     * 比赛选手举报
     * @param reportMessageText 举报信息文本
     * @return
     */
    @ApiOperation("比赛选手举报")
    @GetMapping("/competitorReport")
    public String competitorReport(@RequestParam("reportMessageText") String reportMessageText){
        return conTestSerivce.competitorReport(reportMessageText);
    }

    /**
     * 报名比赛
     * @return
     */
    @ApiOperation("报名比赛")
    @PostMapping("/signUp")
    public String signUpForTheCompetition(@RequestParam("Idemtoken")String Idemtoken, @RequestParam("token")String token,@RequestParam("contestId")Long contestId){
        return conTestSerivce.signUpForTheCompetition(contestId);
    }


}
