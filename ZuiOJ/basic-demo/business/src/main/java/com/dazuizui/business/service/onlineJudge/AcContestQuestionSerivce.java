package com.dazuizui.business.service.onlineJudge;

import com.dazuizui.basicapi.entry.AcContestQuestion;
import com.dazuizui.business.domain.bo.QueryContestSubmissionLogBo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 比赛题目提交业务层操作接口
 */
@Service
public interface AcContestQuestionSerivce {
    /**
     * 提交答案
     */
    public void submitAnswer(AcContestQuestion acContestQuestion,String status);

    /**
     * 查看本题提交日志
     * @return
     */
    public String queryContestSubmissionLog(QueryContestSubmissionLogBo queryContestSubmissionLogBo);
}
