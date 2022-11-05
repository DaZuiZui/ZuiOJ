package com.dazuizui.business.controller;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.business.service.onlineJudge.SystemService;
import com.dazuizui.business.util.ThreadLocalUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/system")
@Api(value = "系统面板控制器",tags = {"系统面板控制器"})
public class SystemController {

    @Autowired
    private SystemService systemService;


    /**
     * 查看threadlocal
     * @return
     */
    @GetMapping("/getThreadLocal")
    public String getThreadLocalOfJWTDate(){
        return JSONArray.toJSONString(ThreadLocalUtil.mapThreadLocalOfJWT);
    }

    /**
     * 获取幂等性函数
     * @return
     */
    @GetMapping("/getNonPowerTokenString")
    public String getNonPower(){
        return systemService.getNonPower();
    }
}
