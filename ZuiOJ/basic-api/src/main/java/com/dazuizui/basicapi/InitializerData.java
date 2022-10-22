package com.dazuizui.basicapi;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * @author Bryan yang(杨易达)
 * 初始化数据
 */
public class InitializerData implements Serializable {
    /**
     * 编程语言环境
     */
    public static HashMap<Long,HashMap<Integer, List<String>>> langSystem = new HashMap<>();
}
