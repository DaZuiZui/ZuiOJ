package com.dazuizui.business.service.admin;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * 表属性
 */
public interface AttributeService {
    /**
     * 获取指定表的全部数据
     * @param table
     * @return
     */
    public String getNumberOfTable(@RequestParam("table") Long table);
}
