package com.dazuizui.business.controller;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.bo.AddProctorBo;
import com.dazuizui.business.service.proctor.ProctorService;
import com.dazuizui.business.util.ThreadLocalUtil;
import io.swagger.annotations.Api;
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
    @PostMapping("/addProctor")
    public String addProctor(@RequestBody AddProctorBo addProctorBo){
        //查看是否权限不足
        Map<String, String> map = ThreadLocalUtil.mapThreadLocal.get();
        if (map.get("error") != null){
            return JSONArray.toJSONString(new ResponseVo<>(map.get("error"),null,map.get("code")));
        }

        return proctorService.addProctor(addProctorBo);
    }
}
