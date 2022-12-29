package com.dazuizui.basicapi.entry;

import java.io.Serializable;

/**
 * 返回状态代码
 */
public class StatusCode  {
    //身份验证过期
    public static String authenticationExpired   = "0x444";
    //权限不足
    public static String insufficientPermissions = "0x445";
    //成功
    public static String OK = "0x200";
    //失败
    public static String Error = "0x500";

}
