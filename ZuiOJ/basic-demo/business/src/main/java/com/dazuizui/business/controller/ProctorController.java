package com.dazuizui.business.controller;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.bo.*;
import com.dazuizui.business.service.onlineJudge.CompetitionInfoService;
import com.dazuizui.business.service.onlineJudge.QuestionBankService;
import com.dazuizui.business.service.onlineJudge.SubmmitionCodeInContestSerivce;
import com.dazuizui.business.service.proctor.ProctorService;
import com.dazuizui.business.util.ThreadLocalUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@Api(value = "面试官版块控制器",tags = {"面试官版块控制器"})
@RequestMapping("/proctor")
@RestController
public class ProctorController {
    @Autowired
    private ProctorService proctorService;
    @Autowired
    private CompetitionInfoService competitionInfoService;
    @Autowired
    private SubmmitionCodeInContestSerivce submmitionCodeInContestSerivce;
    @Autowired
    private QuestionBankService questionBankService;

    /**
     * 添加一个面试官
     * @param addProctorBo
     * @return
     */
    @ApiOperation("添加一个面试官")
    @PostMapping("/admin/addProctor")
    public String addProctor(@RequestBody AddProctorBo addProctorBo){
        //查看是否权限不足
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        ThreadLocalUtil.mapThreadLocal.remove();
        //报错排查
        if ( map.get("error") != null) {
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return proctorService.addProctor(addProctorBo);
    }

    /**
     * 监考人员分页获取未来进行时现在进行时的数据
     * @param proctorGetFutureEventsInfoBo
     * @return
     */
    @ApiOperation("监考人员分页获取未来进行时现在进行时的数据")
    @PostMapping("/proctorGetFutureEventsInfo")
    public String proctorGetFutureEventsInfo(@RequestBody ProctorGetFutureEventsInfoBo proctorGetFutureEventsInfoBo){
        //查看是否权限不足
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        ThreadLocalUtil.mapThreadLocal.remove();

        //报错排查
        if ( map.get("error") != null) {

            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return proctorService.proctorGetFutureEventsInfo(proctorGetFutureEventsInfoBo);
    }

    /**
     * 监考人员分页获取未来过去时的数据
     * @param proctorGetFutureEventsInfoBo
     * @return
     */
    @ApiOperation("监考人员分页获取未来过去时的数据")
    @PostMapping("/proctorGetLastEventsInfo")
    public String proctorGetLastEventsInfo(@RequestBody ProctorGetFutureEventsInfoBo proctorGetFutureEventsInfoBo){
        //查看是否权限不足
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        ThreadLocalUtil.mapThreadLocal.remove();

        //报错排查
        if ( map.get("error") != null) {

            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return proctorService.proctorGetLastEventsInfo(proctorGetFutureEventsInfoBo);
    }

    /**
     * 监考身份的鉴别
     * @param token
     * @return
     */
    @GetMapping("/analysis")
    @ApiOperation("监考身份鉴别")
    public String analysis(@RequestParam("token")String token){
        return proctorService.proctorAnalysis(token);
    }

    /**
     * 分页获取比赛选手信息
     * @param paglingQueryContestantsInThisContestBo
     * @return
     */
    @ApiOperation("分页获取比赛选手信息")
    @PostMapping("/paglingQueryContestantsInThisContest")
    public String paglingQueryContestantsInThisContest(@RequestBody PaglingQueryContestantsInThisContestBo paglingQueryContestantsInThisContestBo){
        return competitionInfoService.paglingQueryContestantsInThisContest(paglingQueryContestantsInThisContestBo);
    }


    /**
     * 筛选查询比赛提交保存的代码
     * @param findByContestIdAndQuestionIdAndUserIdBo
     * @return
     */
    @ApiOperation("筛选查询比赛提交保存的代码")
    @PostMapping("/SubmmitionCodeInContestController/filterQueryMatchSaveCode")
    public String proctorFilterQueryMatchSaveCode(@RequestBody FilterQueryMatchSaveCodeBo findByContestIdAndQuestionIdAndUserIdBo){
        //身份验证过期和权限鉴别
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        ThreadLocalUtil.mapThreadLocal.remove();
        if ( map.get("error") != null) {
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return submmitionCodeInContestSerivce.filterQueryMatchSaveCode(findByContestIdAndQuestionIdAndUserIdBo);
    }


    /**
     * 根据id获取题目
     * @param token
     * @param id  问题id
     * @param contestId 比赛id
     * @return
     */
    @ApiOperation("根据id获取题目")
    @PostMapping("/GetQuestionById")
    public String getQuestionById(@RequestParam("token")String token,@RequestParam("id")Long id,@RequestParam("contestId") Long contestId){
        //身份验证过期或者权限不足
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        ThreadLocalUtil.mapThreadLocal.remove();
        //报错排查
        if ( map.get("error") != null) {
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return questionBankService.adminGetQuestionById(id);
    }

    /**
     * 监考人员通过id查询代码详细信息
     */
    @ApiOperation("通过id查询代码详细信息")
    @PostMapping("/submmitionCodeInContestController/findOneById")
    public String findOneCodeDetailedById(@RequestParam("token")String token,@RequestParam("id")Long id,@RequestParam("contestId")Long contestId){
        //身份验证过期和权限鉴别
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        ThreadLocalUtil.mapThreadLocal.remove();
        if ( map.get("error") != null) {
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return submmitionCodeInContestSerivce.findOneById(id);
    }


    /**
     * 管理员分页获取监考人员数据
     * @param adminGetProctorsByPaginBo
     * @return
     */
    @ApiOperation("管理员分页获取监考人员数据")
    @PostMapping("/adminGetProctorsByPagin")
    public String adminGetProctorsByPagin(@RequestBody AdminGetProctorsByPaginBo adminGetProctorsByPaginBo){
        return proctorService.adminGetProctorsByPagin(adminGetProctorsByPaginBo);
    }


    /**
     * 管理员删除监考人员通过监考人员Id
     * @param adminDeleteProctorByIdBo
     * @return
     */
    @ApiOperation("管理员删除监考人员通过监考人员Id")
    @PostMapping("/adminDeleteProctorByIdOfProctor")
    public String adminDeleteProctorByIdOfProctor(@RequestBody AdminDeleteProctorByIdBo adminDeleteProctorByIdBo){
        return proctorService.adminDeleteProctorByIdOfProctor(adminDeleteProctorByIdBo);
    }
}
