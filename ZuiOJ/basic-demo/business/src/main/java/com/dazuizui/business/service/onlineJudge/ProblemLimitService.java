package com.dazuizui.business.service.onlineJudge;

import com.dazuizui.basicapi.entry.ProblemLimit;
import org.springframework.stereotype.Service;

/**
 * 问题限制业务接口
 */
@Service
public interface ProblemLimitService {
    //增加问题限制
    public String addProblemLimit(ProblemLimit problemLimit);
}
