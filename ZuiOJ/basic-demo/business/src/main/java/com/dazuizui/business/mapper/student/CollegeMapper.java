package com.dazuizui.business.mapper.student;

import com.dazuizui.basicapi.entry.College;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CollegeMapper {
    /**
     * @author 杨易达
     *  查看所有学院，数据极少，暂不进行优化
     * @return
     */
    public List<College> getALlCollegebyUniverstyId(@Param("id") Long id);
}
