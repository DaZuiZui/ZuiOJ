package com.dazuizui.basicapi.entry;

import java.io.Serializable;

/**
 * 状态代码返回信息
 */
public class StatusCodeMessage implements Serializable {
    //身份验证过期
    public static final String authenticationExpired   = "身份严重过期";
    //权限不足
    public static final String insufficientPermissions = "您的操作权限不足";
    //成功
    public static final String OK = "操作成功";
    //失败
    public static final String Error = "操作失败";
    //数据为null
    public static final String IsNull = "服务器接收数据为null，请刷新网页";
    //幂等性操作
    public static final String Idempotency = "幂等性操作，请刷新网页";
}
