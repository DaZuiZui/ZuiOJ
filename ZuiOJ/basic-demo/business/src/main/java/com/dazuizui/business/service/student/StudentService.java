package com.dazuizui.business.service.student;

import com.dazuizui.basicapi.entry.StudentCertification;
import org.springframework.stereotype.Service;

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
}
