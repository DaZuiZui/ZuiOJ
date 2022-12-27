package com.dazuizui.basicapi.entry;

public class RedisKey {
    //状态是0的题库数量
    public static String QuestionCountWithStatusIs0 = "ZuiOJ:question:conut:statusis1";
    //查看任意状态的问题数量
    public static String QuestionCountWithAnyStatus = "ZuiOJ:question:conut:anystatus";
    //问题缓存
    public static String ZuiOJQuestion = "ZuiOJ:Question:";

    public static String ZuiOJQuestionCase = "ZuiOJ:QuestionBack:Case:QuestionID:";
}
