package com.dazuizui.business.service.onlineJudge;

import com.dazuizui.business.domain.AcCodeInContest;
import org.springframework.stereotype.Service;

/**
 * 比赛提交代码业务层
 */
@Service
public interface CodeInContestService {
    /**
     * 添加比赛提交代码
     * @return
     */
    public String insertSubmittionContestCodeInfo(AcCodeInContest acCodeInContest);
}
