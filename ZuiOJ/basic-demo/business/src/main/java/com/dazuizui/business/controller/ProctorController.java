package com.dazuizui.business.controller;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.bo.AddProctorBo;
import com.dazuizui.business.domain.bo.ProctorGetFutureEventsInfoBo;
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
            return JSONArray.toJSONString(new ResponseVo<>(ThreadLocalUtil.mapThreadLocal.get().get("error"),null,ThreadLocalUtil.mapThreadLocal.get().get("code")));
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
    public String proctorGetFutureEventsInfo(ProctorGetFutureEventsInfoBo proctorGetFutureEventsInfoBo){
        //查看是否权限不足
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        ThreadLocalUtil.mapThreadLocal.remove();
        //报错排查
        if ( map.get("error") != null) {
            return JSONArray.toJSONString(new ResponseVo<>(ThreadLocalUtil.mapThreadLocal.get().get("error"),null,ThreadLocalUtil.mapThreadLocal.get().get("code")));
        }

        return proctorService.proctorGetFutureEventsInfo(proctorGetFutureEventsInfoBo);
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
}
