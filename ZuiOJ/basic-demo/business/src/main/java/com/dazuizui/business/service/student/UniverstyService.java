package com.dazuizui.business.service.student;

import com.dazuizui.basicapi.entry.Universty;
import org.springframework.stereotype.Service;

/**
 * 大学业务接口
 */
@Service
public interface UniverstyService {
    /**
     * 查询所有大学
     *     该数据量极少不考虑优化暂时，后续再说，加入todo
     * @return String
     */
    public String queryAllUniversty();
}
