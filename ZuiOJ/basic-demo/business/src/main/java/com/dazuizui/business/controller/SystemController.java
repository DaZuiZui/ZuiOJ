package com.dazuizui.business.controller;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.business.service.onlineJudge.SystemService;
import com.dazuizui.business.util.JwtUtil;
import com.dazuizui.business.util.ThreadLocalUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/system")
@Api(value = "系统面板控制器",tags = {"系统面板控制器"})
public class SystemController {

    @Autowired
    private SystemService systemService;
    @Value("${filePath}")
    private String path;

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

    /**
     * 上传文件
     */
    @ApiOperation("上传图片")
    @RequestMapping(value = "imgUpDown",method = {RequestMethod.POST})
    public String imgUpDown(@RequestParam("file") MultipartFile file,@RequestParam("token")String token) throws IOException {
        return systemService.imgUpDown(file,token);
    }

    /**
     * 文件下载
     */
    @ApiOperation("文件下载")
    @GetMapping(value ="/getimage",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getPhoto(@RequestParam("imgUrl") String imgUrl) throws IOException {
        return systemService.getPhoto(imgUrl);
    }
}
