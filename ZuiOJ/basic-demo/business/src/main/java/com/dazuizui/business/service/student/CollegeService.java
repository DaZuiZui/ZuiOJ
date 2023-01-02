package com.dazuizui.business.service.student;

import org.springframework.stereotype.Service;

/**
 * 学院板块业务接口
 */
@Service
public interface CollegeService {
    /**
     * @author 杨易达
     *  查看所有学院，数据极少，暂不进行优化
     * @return
     */
    public String getALlCollegebyUniverstyId(Long id);
}
