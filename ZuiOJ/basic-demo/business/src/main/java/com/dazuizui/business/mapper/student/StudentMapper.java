package com.dazuizui.business.mapper.student;

import com.dazuizui.basicapi.entry.StudentCertification;
import com.dazuizui.basicapi.entry.bo.StudentCertificationBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 学生板块控制器
 */
@Mapper
public interface StudentMapper {

    /**
     * 学生认证
     * @param studentCertificationBo
     * @return
     */
    public Long studentCertification(StudentCertification studentCertificationBo);

    /**
     * 通过学生id获取学生信息
     * @return
     */
    public StudentCertification queryStudentInfoByUserId(@Param("userId") Long userId);
}
