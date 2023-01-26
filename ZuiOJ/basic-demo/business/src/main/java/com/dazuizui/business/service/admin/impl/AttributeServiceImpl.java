package com.dazuizui.business.service.admin.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.StatusCodeMessage;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.mapper.AttributeMapper;
import com.dazuizui.business.service.admin.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 获取只定表的全部数据
 */
@Service
public class AttributeServiceImpl implements AttributeService {

    @Autowired
    private AttributeMapper attributeMapper;

    /**
     * 获取制定表的全部数据
     * @param table
     * @return
     */
    @Override
    public String getNumberOfTable(Long table) {
        Long aLong = attributeMapper.queryNumberOfTable(table);
        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,aLong, StatusCode.OK));
    }
}
