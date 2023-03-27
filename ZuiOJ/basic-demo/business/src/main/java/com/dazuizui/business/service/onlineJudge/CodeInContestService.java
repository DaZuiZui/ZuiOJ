package com.dazuizui.business.service.onlineJudge;

import com.dazuizui.business.domain.CodeInContest;
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
    public String insertSubmittionContestCodeInfo(CodeInContest acCodeInContest);
}
