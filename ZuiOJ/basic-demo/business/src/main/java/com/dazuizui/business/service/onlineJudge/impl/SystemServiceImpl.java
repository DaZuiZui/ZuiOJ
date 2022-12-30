package com.dazuizui.business.service.onlineJudge.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.service.onlineJudge.SystemService;
import com.dazuizui.business.util.JwtUtil;
import com.dazuizui.business.util.RedisUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * 系统接口实现类
 */
@Service
public class SystemServiceImpl implements SystemService {
    @Autowired
    private RedisUtil redisUtil;
    @Value("${filePath}")
    private String path;

    /**
     * 非幂等性处理
     * @return
     */
    @Override
    public String getNonPower() {
        String uuid = UUID.randomUUID().toString();
        redisUtil.setStringInRedis(uuid,60*5,1);
        return JSONArray.toJSONString(new ResponseVo<>("幂等性token",uuid,"0x99999"));
    }

    /**
     * 上传图片
     */
    @Override
    public String imgUpDown(@RequestParam("file") MultipartFile file, @RequestParam("token")String token) throws IOException{
        //获取文件名
        String fileName = file.getOriginalFilename();
        //获取文件后缀名。也可以在这里添加判断语句，规定特定格式的图片才能上传，否则拒绝保存。
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //为了避免发生图片替换，这里使用了文件名重新生成
        fileName = UUID.randomUUID()+suffixName;

        //获取username
        Map<String, Object> analysis = JwtUtil.analysis(token);
        String username = (String) analysis.get("username");

        File saveFilePath = new File(path+""+username+"/");
        //判断是否存在文件夹，不存在就创建，但其实可以直接手动确定创建好，这样不用每次保存都检测
        if (!saveFilePath.exists()){
            saveFilePath.mkdirs();
        }

        file.transferTo(new File(path+""+username+"/"+fileName));

        return "http://localhost:5050/img/"+fileName;
    }
}
