package com.dazuizui.business.service.student.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.Lesson;
import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.mapper.student.LessonMapper;
import com.dazuizui.business.service.student.LessonService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 班级业务实现
 */
@Service
public class LessonServiceImpl implements LessonService {
    @Autowired
    private LessonMapper lessonMapper;

    /**
     * 通过专业id来查询班级，
     *      数据量极少暂不做优化
     * @param id
     * @return
     */
    @Override
    public String ueryAllLessonByMajorId(@Param("id")Long id){
        List<Lesson> lessons = lessonMapper.queryAllLessonByMajorId(id);
        return JSONArray.toJSONString(new ResponseVo<>("获取成功",lessons, StatusCode.OK));
    }
}
