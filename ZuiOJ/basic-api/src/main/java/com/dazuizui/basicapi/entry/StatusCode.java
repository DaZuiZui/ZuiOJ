package com.dazuizui.basicapi.entry;

import java.io.Serializable;

/**
 * 返回状态代码
 */
public class StatusCode  {
    //身份验证过期
    public static final String authenticationExpired   = "0x444";
    //权限不足
    public static final String insufficientPermissions = "0x445";
    //成功
    public static final String OK = "0x200";
    //失败
    public static final String Error = "0x500";
    //数据为null
    public static final String IsNull = "0x501";
    //幂等性操作
    public static final String Idempotency = "0x502";
    //数据为null
    public static final String DateIsNull = "0x503";
    //无权查看文章
    public static final String NotAuthorizedToView = "0x504";
    //无权查看比赛
    public static final String NotAuthorizedToContest = "0x505";
    //该用户名不存在
    public static final String ThisUsernameDoesNotExist = "0x506";
    //该用户已经是监考人员
    public static final String IsProctor = "0x507";
}
