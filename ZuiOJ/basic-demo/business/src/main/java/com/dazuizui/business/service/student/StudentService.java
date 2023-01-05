package com.dazuizui.business.service.student;

import com.dazuizui.basicapi.entry.StudentCertification;
import com.dazuizui.basicapi.entry.vo.GetStudentInfoVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户业务实现
 */
@Service
public interface StudentService {
    /**
     * 学生认证
     * @param studentCertificationBo
     * @return
     */
    public String studentCertification(StudentCertification studentCertificationBo);

    /**
     * 获取学生认证信息
     */
    public String getStudentInfo();

    /**
     * 通过认证主键获取学生信息
     */
    public GetStudentInfoVo queryStudentByUserId(Long UserId);

    /**
     * 修改学生信息
     * @param studentCertificationBo
     * @return
     */
    public String updateStudentCertification(StudentCertification studentCertificationBo);
}
