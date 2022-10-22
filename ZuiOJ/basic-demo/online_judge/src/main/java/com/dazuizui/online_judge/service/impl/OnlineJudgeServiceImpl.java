package com.dazuizui.online_judge.service.impl;

import cn.hutool.core.util.ObjectUtil;
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
    public JSONObject judgeTheProgram(ProgramBo programBo) {
        JSONObject cmd = new JSONObject();
        //设置args参数
        cmd.set("args",programBo.getParseCodeArgs());
        //设置env
        cmd.set("env",Arrays.asList("PATH=/usr/bin:/bin"));

        //设置cmd的file参数
        JSONArray files = new JSONArray();
        files.put(new JSONObject().set("name","stdout").set("max",programBo.getProblemLimit().getStdoutMax()));
        files.put(new JSONObject().set("name","stderr").set("max",programBo.getProblemLimit().getStderrMax()));
        cmd.set("files", files);
        // 设置cpu时间限制
        cmd.set("cpuLimit",programBo.getProblemLimit().getCpuLimit()*1000*1000*1000L);
        // 设置内存限制
        cmd.set("memoryLimit",programBo.getProblemLimit().getMemoryLimit()*1024*1024L);
        // 设置线程限制
        cmd.set("procLimit",programBo.getProblemLimit().getProcLimit());
        //设置copyin以及代码内容
        JSONObject copyIn = new JSONObject();
        copyIn.set(programBo.getCopyOutCached().get(0),new JSONObject().set("content","public class Main{  public static void main(String[] args){    System.out.println(\"hello world\");  }}"));

        cmd.set("copyIn", copyIn);
        // 设置copyOut
        cmd.set("copyOut", Arrays.asList("stdout","stderr"));
        // 设置文件缓存
        cmd.set("copyOutCached", programBo.getCopyOutCached());

        JSONArray request = request(cmd);
        JSONObject jsonObject = new JSONObject(request.get(0));
        //获取返回状态
        String status = jsonObject.get("status").toString();
        /**
         * 如果通过就去查看执行结果，如果不通过就返回状态
         */
        if (status.equals("Accepted")){
            JSONObject jsonObject1 = new JSONObject(jsonObject.get("fileIds"));
            String fileUUID = jsonObject1.get(programBo.getCopyOutCached().get(1)).toString();
            JSONArray jsonArray = executeCode(fileUUID, programBo.getInput(), programBo);
            JSONObject jsonObject2 = new JSONObject(jsonArray.get(0));
            System.err.println(jsonObject2);
            return jsonObject2;
        }

        return jsonObject;
    }

    /**
     * For me, there is no 5am LA, only 5am programmers fighting for their lives  -Bryan yang
     * 对我而言，没有凌晨五点的洛杉矶，只有凌晨5点的程序员为生活而奋斗                         (Bryan yang)杨易达
     *                                                                             10/23/2022 5:21 Am
     * 代码执行
     * @param fileId
     * @param input
     */
    public JSONArray executeCode(String fileId,String input,ProgramBo programBo){
        JSONObject cmd = new JSONObject();
        System.out.println(programBo.getRunCommandArgs());
        //args配置
        cmd.set("args",programBo.getRunCommandArgs());
        //设置env
        cmd.set("env",Arrays.asList("PATH=/usr/bin:/bin"));
        // 设置cmd的files参数
        JSONArray files = new JSONArray();
        //设置输入样例
        if (ObjectUtil.isNotNull(input)){
            files.put(new JSONObject().set("content",input));
        }else{
            files.put(new JSONObject().set("content",""));
        }
        files.put(new JSONObject().set("name","stdout").set("max",programBo.getProblemLimit().getStdoutMax()));
        files.put(new JSONObject().set("name","stderr").set("max",programBo.getProblemLimit().getStderrMax()));
        cmd.set("files", files);
        // 设置cpu时间限制
        cmd.set("cpuLimit",programBo.getProblemLimit().getCpuLimit()*1000*1000*1000L);
        // 设置内存限制
        cmd.set("memoryLimit",programBo.getProblemLimit().getMemoryLimit()*1024*1024L);
        // 设置线程限制
        cmd.set("procLimit",programBo.getProblemLimit().getProcLimit());
        JSONObject copyIn = new JSONObject();
        copyIn.set("Main.class",new JSONObject().set("fileId",fileId));
        cmd.set("copyIn", copyIn);

        JSONArray request = request(cmd);
        return request;
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
