package com.dazuizui.business.service.onlineJudge;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 系统接口
 */
@Service
public interface SystemService {
    /**
     * 幂等性处理
     * @return
     */
    public String getNonPower();

    /**
     * 上传图片
     */
    public String imgUpDown(@RequestParam("file") MultipartFile file, @RequestParam("token")String token) throws IOException;
}
