package com.dazuizui.business.service.invigilator;

import com.dazuizui.business.domain.Invigilator;
import com.dazuizui.business.domain.bo.FindByUserIdAndStatusAndDelFlagBo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * 监考业务接口
 */
@Service
public interface InvigilatorService {
    /**
     * 增加面试官
     * @param invigilator
     * @return
     */
    public String addInvigilator(Invigilator invigilator);

    /**
     * 通过userId查询数据
     * @return
     */
    public String findByUserIdAndStatusAndDelFlag(FindByUserIdAndStatusAndDelFlagBo findByUserIdAndStatusAndDelFlagBo );
}
