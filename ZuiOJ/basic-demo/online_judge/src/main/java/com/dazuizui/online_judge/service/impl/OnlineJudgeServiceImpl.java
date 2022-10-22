package com.dazuizui.online_judge.service.impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.dazuizui.basicapi.entry.bo.ProgramBo;
import com.dazuizui.online_judge.service.OnlineJudgeService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @author Bryan yang(杨易达)
 * 在线判断业务接口实现类
 */
@Service
public class OnlineJudgeServiceImpl implements OnlineJudgeService {
    /**
     * 判决代码业务实现类
     * @param programBo
     * @return
     */
    @Override
    public String judgeTheProgram(ProgramBo programBo) {
        System.err.println("----------"+programBo);

        JSONObject cmd = new JSONObject();
        //设置args参数
        cmd.set("args",programBo.getParseCodeArgs());
        //设置env
        cmd.set("env",programBo.getEnv());

        //设置cmd的file参数
        JSONArray files = new JSONArray();
        files.put(new JSONObject().set("name","stdout").set("max",10240));
        files.put(new JSONObject().set("name","stderr").set("max",10240));
        cmd.set("files", files);
        // 设置cpu时间限制
        cmd.set("cpuLimit",5*1000*1000*1000L);
        // 设置内存限制
        cmd.set("memoryLimit",256*1024*1024L);
        // 设置线程限制
        cmd.set("procLimit",1024);
        //设置copyin以及代码内容
        JSONObject copyIn = new JSONObject();
        copyIn.set("Main.java",new JSONObject().set("content","public class Main{  public static void main(String[] args){    System.out.println(\"hello world\");  }}"));

        cmd.set("copyIn", copyIn);
        // 设置copyOut
        cmd.set("copyOut", Arrays.asList("stdout","stderr"));
        // 设置文件缓存
        cmd.set("copyOutCached", programBo.getCopyOutCached());

        JSONArray request = request(cmd);
        System.err.println(request);

        return null;
    }

    public JSONArray request(JSONObject cmd){
        RestTemplate restTemplate = new RestTemplate();
        JSONObject cmd1 = new JSONObject().set("cmd", new JSONArray().put(cmd));
        System.err.println(cmd1);
        System.err.println();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(JSONUtil.toJsonStr(cmd1), headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://192.168.170.128:5050/run",request,String.class);

        return JSONUtil.parseArray(responseEntity.getBody());
    }
}
