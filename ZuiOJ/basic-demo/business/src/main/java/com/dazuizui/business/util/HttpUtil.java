package com.dazuizui.business.util;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.dazuizui.basicapi.entry.bo.ProgramBo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


public class HttpUtil {


    public static JSONArray request(ProgramBo programBo){
        RestTemplate restTemplate = RestTemplateUtil.getInstance("utf-8");
        String url = "http://127.0.0.1:8005/sandbox/judge";
        String result = restTemplate.postForObject(url, programBo, String.class);
        System.out.println("\n\n"+result);

        return null;
    }
}
