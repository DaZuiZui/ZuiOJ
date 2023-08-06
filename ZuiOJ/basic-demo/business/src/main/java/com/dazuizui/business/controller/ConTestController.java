package com.dazuizui.business.controller;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.Contest;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.vo.AdminGetArticleByPaginVo;
import com.dazuizui.business.service.onlineJudge.ContestSerivce;
import com.dazuizui.business.util.ThreadLocalUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Auhtor 杨易达
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
     * @author Bryan Yang(杨易达)
     * remove the contest
     *     in terms of aop:
     *        in terms of aop,it is mainly done to ensure that the operation authority is an admin
     *     on the business layer:
     *        on the business layer,the profile info and detailed info of the competition,the invigilators of the
     *     examination room ,the contest info date of competition cached in redis and records of contestands are deleted
     *
     *
     * 移除比赛信息
     *     在aop层:
     *        在Aop方面主要做了确保操作权限为管理员。
     *     在业务层
     *        在业务层上进行删除了比赛的简介信息和比赛的详细信息和该考场的监考人员和redis中缓存的比赛内容信息数据和所有参赛选手的记录。
     *
     * @return String
     */
    @ApiOperation("移除比赛页面")
    @PostMapping("/admin/removeTheContestById")
    public String removeTheContestById(@RequestParam("token") String token,@RequestParam("id")Long id){
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        ThreadLocalUtil.mapThreadLocal.remove();
        if ( map.get("error") != null) {
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return conTestSerivce.removeTheContestById(id);
    }

    /**
     * @auhtor Bryan Yang
     * 修改比赛信息
     *     Aop层
     *        确保了操作身份必须为管理员
     *     业务层
     *        修改了比赛的简介信息和详细信息和更新redis事务
     *
     * modifyed contest data
     *     in term of aop
     *        in terms of aop,it is mainly done to ensure that the operation authority is an admin
     *     on the business layer:
     *        modifyed the profile info and details of the contet and updated the redis data
     */
    @ApiOperation("修改比赛信息")
    @PostMapping("/updateContest")
    public String updateContest(@RequestParam("token")String token,@RequestBody Contest contest){
        //身份验证过期
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        ThreadLocalUtil.mapThreadLocal.remove();
        //报错排查
        if ( map.get("error") != null) {
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return conTestSerivce.updateContest(contest);
    }

    /**
     * @authro Bryan Yang(Dazui)
     *
     * 分页查询竞赛数据
     * Admin query game data by page
     *
     * Aop层：
     *    无Aop层鉴权操作，因为在最开始设计的时候出现的疏忽，如果需要进行改动则任务量太大。用户、游客、管理员共享此接口。
     * 业务层：
     *    根据AdminQueryGameInformationByPageVo查询条件进行分页查询。
     *
     *    要进行改动则任务量太大。用户、游客、管理员共享此接口。
     *
     * Aop layer:
     *    There is no Aop layer authentication operation, because of the negligence in the initial design, if changes
     *    are required, the workload will be too large. Users, visitors, administrators share this interface.
     * Business Layer:
     *    Perform pagination query according to AdminQueryGameInformationByPageVo query conditions
     *
     * @param adminQueryGameInformationByPageBo 分页
     * @return String
     */
    @PostMapping("/adminQueryGameInformationByPage")
    @ApiOperation("分页查询竞赛数据")
    public String adminQueryGameInformationByPage(@RequestBody AdminGetArticleByPaginVo.AdminQueryGameInformationByPageVo adminQueryGameInformationByPageBo){
        return conTestSerivce.adminQueryGameInformationByPage(adminQueryGameInformationByPageBo);
    }

    /**
     * 提交比赛
     * Creat Contest
     *
     * Aop层:
     *     保证操作者是Admin
     * 业务层
     *     该接口实现做了将比赛信息添加到数据库和初始化当前赛事的监考信息和设置对应的比赛信息缓存。
     *
     * Aop layer:
     *     in terms of aop,it is mainly done to ensure that the operation authority is an admin
     * Business layer:
     *      this interface impl adds competition info to the DB,initialize the invigilation info for the current competition
     *      ,and sets the corresponding competition info Redis cache.
     *
     * @param conTest   比赛实体
     * @param token     操作者token
     * @param Idemtoken 验证幂等性操作token
     * @return String
     */
    @ApiOperation("提交比赛")
    @PostMapping("/postContest")
    public String postContest(@RequestParam("Idemtoken")String Idemtoken,@RequestParam("token")String token,@RequestBody Contest conTest){
        //身份验证过期
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        ThreadLocalUtil.mapThreadLocal.remove();
        if ( map.get("error") != null) {
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return conTestSerivce.postContest(conTest);
    }

    /**
     * @authro Bryan Yang(大嘴) 16/010/2022
     *
     * 获取未来赛制
     * get future events
     *
     * @return String
     */
    @ApiOperation("查询未开始的比赛或者赛制")
    @GetMapping("/futureEvents")
    public String getFutureEvents(){
        return conTestSerivce.getFutureEvents();
    }

    /**
     * @author Bryan Yang(Dazui)
     * 获取全部赛制
     *
     * Aop
     *   无处理
     *
     * 业务层
     *  获取未来进行时赛事和以前进行时赛事。
     *
     * Aop
     *   no processing
     *
     * Business Layer
     *   Get future on-going events and previous on-going events.
     *
     * @return String
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
