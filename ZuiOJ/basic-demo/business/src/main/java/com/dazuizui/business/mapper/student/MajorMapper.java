package com.dazuizui.business.mapper.student;

import com.dazuizui.basicapi.entry.Major;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 大学专业持久层 Mapper层
 */
@Mapper
public interface MajorMapper {
    /**
     * 通过学院id查询所有专业
     * @param id
     * @return
     */
    public List<Major> getAllMajorByCollegeId(@Param("id")Long id);
}
