package com.dazuizui.business.util;

import java.util.HashMap;
import java.util.Map;

/**
 * ThreadLocal 工具类
 */
public class ThreadLocalUtil {
    public static ThreadLocal<Map<String,Map<String,Object>>> mapThreadLocalOfJWT
            = ThreadLocal.withInitial(()-> new HashMap<String,Map<String,Object>>());
}
