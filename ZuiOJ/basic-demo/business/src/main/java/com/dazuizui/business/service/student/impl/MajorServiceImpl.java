package com.dazuizui.business.service.student.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.Major;
import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.mapper.student.MajorMapper;
import com.dazuizui.business.service.student.MajorService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 大学专业业务实现类
 */
@Service
public class MajorServiceImpl implements MajorService {
    @Autowired
    private MajorMapper majorMapper;

    /**
     * 通过学院id查询所有专业
     *  目前没有做缓存层优化，因为数据量极少
     *  There is currenly no cahing layer opimization,because the amount of date is very small
     * @param id    学院ID
     * @return
     */
    @Override
    public String getAllMajorByCollegeId(@Param("id")Long id){
        List<Major> allMajorByCollegeId = majorMapper.getAllMajorByCollegeId(id);
        return JSONArray.toJSONString(new ResponseVo<>("获取成功",allMajorByCollegeId, StatusCode.OK));
    }
}
