package com.dazuizui.business.util;

import com.dazuizui.basicapi.entry.ApplicationKey;
import com.dazuizui.basicapi.entry.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 哈尔滨广厦学院JWT系统
 */
public class JwtUtil {

    /**
     * 生成密文
     * @return
     */
    public static String createJWT(User user){
        System.err.println(user);
        //构建jwt令牌
        JwtBuilder builder = Jwts.builder();
        builder.setIssuer("DaZuiZui"); //颁发者
        builder.setIssuedAt(new Date()); //颁发时间
        builder.setSubject("ZuiOJ-GuangSha-TLMOJ");   //主题
        builder.setExpiration(new Date(System.currentTimeMillis()+3600000*24*7));  //过期时间 设置3600秒

        //自定义信息 自定义载荷
        Map<String,Object> map = new HashMap<>();
        //防止不能存储Long
        String idString = String.valueOf(user.getId());
        map.put("id",idString);
        map.put("username",user.getUsername());
        map.put("studentId",user.getStudentId());
        map.put("name",user.getName());
        map.put("sex",user.getSex());
        map.put("idCard",user.getIdCard());
        map.put("collegeId",user.getCollegeId());
        map.put("majorId",user.getMajorId());
        map.put("classId",user.getClassId());
        map.put("role",user.getRole());
        map.put("grade",user.getGrade());
        map.put("status",user.getStatus());
        map.put("headPortrait",user.getHeadPortrait());
        builder.addClaims(map); //添加载荷

        //1.算法 2.密钥
        builder.signWith(SignatureAlgorithm.HS256, ApplicationKey.JWTKey);

        //生成密文
        String jstString = builder.compact();
        return jstString;
    }


    /**
     * 解析
     * @param token
     * @return
     */
    public static Map<String,Object> analysis(String token){
        Claims body = Jwts.parser()
                //密钥
                .setSigningKey(ApplicationKey.JWTKey)
                //要解析的令牌
                .parseClaimsJws(token)
                //获取解析后的数据
                .getBody();
        return body;
    }
}