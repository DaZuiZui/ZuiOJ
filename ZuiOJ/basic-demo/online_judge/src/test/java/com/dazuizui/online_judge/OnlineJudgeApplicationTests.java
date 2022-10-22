package com.dazuizui.online_judge;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@SpringBootTest
class OnlineJudgeApplicationTests {


    @Autowired
    private RestTemplate restTemplate;

    /**
     * @author Bryan Yang(杨易达)
     * 发起一个Java代码编译
     */
    @Test
    void sendACode() {
        JSONObject cmd = new JSONObject();
        //设置args参数
        List<String> strings = Arrays.asList("/usr/bin/javac", "-encoding", "utf-8", "Main.java");
        cmd.set("args",strings);
        //设置env
        cmd.set("env",Arrays.asList("PATH=/usr/bin:/bin"));

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
        copyIn.set("Main.java",new JSONObject().set("content","public class Lain{  public static void main(String[] args){    System.out.println(\"hello world\");  }}"));
        //copyIn.set("Main.java",new JSONObject().set("content","public class Main{  public static void main(String[] args){    while(true){}  }}"));
        /*
        copyIn.set("Main.java",new JSONObject().set("content","import java.util.Scanner;\n" +
                "\n" +
                "public class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "        Scanner scanner = new Scanner(System.in);\n" +
                "        int a = scanner.nextInt();\n" +
                "        int b = scanner.nextInt();\n" +
                "        System.out.println(a+b);\n" +
                "    }\n" +
                "}"));

         */
        cmd.set("copyIn", copyIn);
        // 设置copyOut
        cmd.set("copyOut", Arrays.asList("stdout","stderr"));
        // 设置文件缓存
        cmd.set("copyOutCached", Arrays.asList("Main.java", "Main.class"));

        JSONArray request = request(cmd);
        JSONObject jsonObject = new JSONObject(request.get(0));
        System.out.println(jsonObject);
        //获取返回状态
        String status = jsonObject.get("status").toString();
        /**
         * 如果通过就去查看执行结果，如果不通过就返回状态
         */
        if (status.equals("Accepted")){
            JSONObject jsonObject1 = new JSONObject(jsonObject.get("fileIds"));
             // System.out.println(jsonObject.get("fileIds"));
            String fileUUID = jsonObject1.get("Main.class").toString();
            executeCode(fileUUID,"1 1");
        }else{
            System.err.println("class name not is Main.java"+status);
        }

    }

    public void executeCode(String fileId,String input){
        JSONObject cmd = new JSONObject();
        //args配置
        cmd.set("args",Arrays.asList("/usr/bin/java","Main"));
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
        files.put(new JSONObject().set("name","stdout").set("max",10240));
        files.put(new JSONObject().set("name","stderr").set("max",10240));
        cmd.set("files", files);
        // 设置cpu时间限制
        cmd.set("cpuLimit",5*1000*1000*1000L);
        // 设置内存限制
        cmd.set("memoryLimit",256*1024*1024L);
        // 设置线程限制
        cmd.set("procLimit",1024);
        JSONObject copyIn = new JSONObject();
        copyIn.set("Main.class",new JSONObject().set("fileId",fileId));
        cmd.set("copyIn", copyIn);
        //System.err.println(cmd);

        JSONArray request = request(cmd);
        System.out.println(request);
    }

    public JSONArray request(JSONObject cmd){
        JSONObject cmd1 = new JSONObject().set("cmd", new JSONArray().put(cmd));
        System.err.println(cmd1);
        System.err.println();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(JSONUtil.toJsonStr(cmd1), headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://192.168.170.128:5050/run",request,String.class);


        return JSONUtil.parseArray(responseEntity.getBody());
    }

    public void executeProgram(){

    }
}
