package com.dazuizui.business.controller;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 比赛团队业务接口
 */
@CrossOrigin
@RestController
@RequestMapping("/team")
@Api(value = "比赛团队控制器",tags = {"比赛团队控制器"})
public class TeamController {

    @PostMapping("/generateMatchTeamsByExcel")
    public String generateMatchTeamsByExcel(@RequestParam("file") MultipartFile file,@RequestParam("token")String token) throws IOException {
        System.out.println("收到的token"+token);
        return "";
    }
}
