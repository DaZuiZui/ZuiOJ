package com.dazuizui.business.service.student.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.College;
import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.mapper.student.CollegeMapper;
import com.dazuizui.business.service.student.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 学院板块业务接口
 */
@Service
public class CollegeServiceImpl implements CollegeService {
    @Autowired
    private CollegeMapper collegeMapper;
    /**
     * @author 杨易达
     *  查看所有学院，数据极少，暂不进行优化
     * @return
     */
    @Override
    public String getALlCollegebyUniverstyId(Long id){
        List<College> aLlCollege = collegeMapper.getALlCollegebyUniverstyId(id);
        return JSONArray.toJSONString(new ResponseVo<>("获取成功",aLlCollege, StatusCode.OK));
    }
}
