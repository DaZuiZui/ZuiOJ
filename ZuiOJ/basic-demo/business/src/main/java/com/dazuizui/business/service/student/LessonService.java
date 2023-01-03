package com.dazuizui.business.service.student;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * 班级板块业务接口
 */
@Service
public interface LessonService {
    /**
     * 通过专业id来查询班级，
     *      数据量极少暂不做优化
     * @param id
     * @return
     */
    public String ueryAllLessonByMajorId(@Param("id")Long id);
}
