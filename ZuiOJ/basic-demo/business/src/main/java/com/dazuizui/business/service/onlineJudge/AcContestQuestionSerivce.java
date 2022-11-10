package com.dazuizui.business.service.onlineJudge;

import com.dazuizui.basicapi.entry.AcContestQuestion;
import org.springframework.stereotype.Service;

/**
 * 比赛题目提交业务层操作接口
 */
@Service
public interface AcContestQuestionSerivce {
    /**
     * 提交答案
     */
    public void submitAnswer(AcContestQuestion acContestQuestion,String status);
}
