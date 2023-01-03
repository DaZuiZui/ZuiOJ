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

}
