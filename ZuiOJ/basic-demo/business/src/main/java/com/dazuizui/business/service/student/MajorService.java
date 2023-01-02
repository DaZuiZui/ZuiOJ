package com.dazuizui.business.service.student;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * 大学专业业务接口
 */
@Service
public interface MajorService {
    /**
     * 通过学院id查询所有专业
     *  目前没有做缓存层优化，因为数据量极少
     *  There is currenly no cahing layer opimization,because the amount of date is very small
     * @param id
     * @return
     */
    public String getAllMajorByCollegeId(@Param("id")Long id);
}
