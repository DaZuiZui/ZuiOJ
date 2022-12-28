package com.dazuizui.basicapi.entry;

public class RedisKey {
    //状态是0的题库数量
    public static String QuestionCountWithStatusIs0 = "ZuiOJ:question:conut:statusis1";
    //查看任意状态的问题数量
    public static String QuestionCountWithAnyStatus = "ZuiOJ:question:conut:anystatus";
    //问题缓存
    public static String ZuiOJQuestion = "ZuiOJ:Question:";
    //获取指定题目的案例
    public static String ZuiOJQuestionCase = "ZuiOJ:QuestionBack:Case:QuestionID:";
    //用户数量
    public static String ZuiBloguserCount = "ZuiBlog:User:count";
    //过期时间
    public static Long OutTime = Long.valueOf(60*60*24*15);
    //用户主键个人信息
    public static String ZuiBlogUserId = "ZuiBlog:User:Info:Id:";
    //用户索引username
    public static String ZuiBlogUserUsername = "ZuiBlog:User:Info:Username:";
}
