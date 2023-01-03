package com.dazuizui.business.mapper.student;

import com.dazuizui.basicapi.entry.Lesson;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 班级持久层
 */
@Mapper
public interface LessonMapper {
    /**
     * 查询所有的班级
     * @return
     */
    public List<Lesson> queryAllLessonByMajorId(@Param("id")Long id);
}
