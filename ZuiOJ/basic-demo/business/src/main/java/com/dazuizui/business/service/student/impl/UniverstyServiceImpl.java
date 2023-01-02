package com.dazuizui.business.service.student.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.Universty;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.mapper.student.UniverstyMapper;
import com.dazuizui.business.service.student.UniverstyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 大学业务接口
 */
@Service
public class UniverstyServiceImpl implements UniverstyService {
    @Autowired
    private UniverstyMapper universtyMapper;
    /**
     * 查询所有大学
     *     该数据量极少不考虑优化暂时，后续再说，加入todo
     * @return List<Universty>
     */
    @Override
    public String queryAllUniversty(){
        List<Universty> universties = universtyMapper.queryAllUniversty();
        return JSONArray.toJSONString(new ResponseVo<>("获取成功",universties, StatusCode.OK));
    }
}
