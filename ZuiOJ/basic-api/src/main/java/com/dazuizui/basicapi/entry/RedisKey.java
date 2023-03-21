package com.dazuizui.basicapi.entry;

public class RedisKey {
    //状态是0的题库数量
    public static final String QuestionCountWithStatusIs0 = "ZuiOJ:question:conut:statusis1";
    //查看任意状态的问题数量
    public static final String QuestionCountWithAnyStatus = "ZuiOJ:question:conut:anystatus";
    //问题缓存
    public static final String ZuiOJQuestion = "ZuiOJ:Question:";
    //获取指定题目的案例
    public static final String ZuiOJQuestionCase = "ZuiOJ:QuestionBack:Case:QuestionID:";
    //用户数量
    public static final String ZuiBlogUserCount = "ZuiBlog:User:count";
    //过期时间
    public static final  Long OutTime = Long.valueOf(60*60*24*15);
    //用户token过期时间
    public static final  Long UserTokenOutTime = Long.valueOf(60*60*24*15);
    //用户主键个人信息
    public static final String ZuiBlogUserId = "ZuiBlog:User:Info:Id:";
    //用户索引username
    public static final String ZuiBlogUserUsername = "ZuiBlog:User:Info:Username:";
    //学生认证Key通过id
    public static final String ZuiOjStudentCertificationUserId = "ZuiOJ:User:Student:userId:";
    //学生认证Key通过username
    public static final String ZuiOjStudentCertificationUsername = "ZuiOJ:User:Student:Username:";
    //博文简介
    public static final String ZuiBlogArticle = "ZuiBlog:Blog:Attribute:";
    //博文Md文件
    public static final String ZuiBlogArticleMd = "ZuiBlog:Blog:Attribute:Md:";
    //题解数量
    public static final String ZuiOJNumberOfQustionAnswer = "ZuiOj:Question:Answer:Numbers:All";
    //状态题解数量
    // 0 公开
    // 1 私有
    // 2 封禁
    public static final String ZuiOJQuestionAnswerPrivicy = "ZuiOJ:Question:Answer:Numbers:Privicy:";
    //当前题的题解总数量
    public static final String ZuiOJQuestionAnswerOf = "ZuiOJ:Question:Answer:Numbers:Id:";
    //当前题的题解指定状态总数量
    //RedisKey.ZuiOJQuestionAnswerPrivicyOf+articleBo.getPrivacy()+":"+articleBo.getId()
    public static final String ZuiOJQuestionAnswerPrivicyOf = "ZuiOJ:Question:Answer:Numbers:Id:";
    //竞赛信息
    public static final String ZuiOJContestInfo = "ZuiOJ:Contest:Info:Id:";
    //问题limit限制
    public static final String ZuiOJQuestionLimit = "ZuiOJ:Question:Limit:Id:";
    //题目案例个数
    //0 全部
    //1 公开
    //2 删除
    public static final String ZuiOJQuestionStatusCount = "ZuiOJ:Question:Count:";
    //比赛选手报名信息 RedisKey.ZuiOJConetstCompetitionInfo + competitionInfo.getContestId() + ":" + id
    public static final String ZuiOJConetstCompetitionInfo = "ZuiOJ:Conetst:CompetitionInfo:Contest:";
    //用户token
    public static final String ZuiBlogUserToken = "ZuiBlog:User:Token";
}
