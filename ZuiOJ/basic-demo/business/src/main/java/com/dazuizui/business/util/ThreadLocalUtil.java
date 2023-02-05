package com.dazuizui.business.util;

import java.util.HashMap;
import java.util.Map;

/**
 * ThreadLocal 工具类
 */
public class ThreadLocalUtil {
    public static ThreadLocal<Map<String,Map<String,Object>>> mapThreadLocalOfJWT
            = ThreadLocal.withInitial(()-> new HashMap<String,Map<String,Object>>());

    /**
     *  Aop切面数据共享，如 aop切面发现出现问题，把问题返回给前端
     */
    public static ThreadLocal<Map<String, String>> mapThreadLocal = ThreadLocal.withInitial(()->new HashMap<String, String>());

    /**
     * 线程内共享数据
     */
    public static ThreadLocal<Map<String, Object>> DataOfThreadLocal = ThreadLocal.withInitial(()->new HashMap<String, Object>());
}
