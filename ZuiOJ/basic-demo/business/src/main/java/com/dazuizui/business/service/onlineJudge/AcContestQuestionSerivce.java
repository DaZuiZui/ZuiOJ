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
     * 删除此用户的提交记录通过Id
     * @param id
     * @return
     */
    public String deleteLogById(@Param("id")Long id);

    /**
     * 提交答案
     */
    public void submitAnswer(AcContestQuestion acContestQuestion,String status);

    /**
     * 查看本题提交日志
     * @return
     */
    public String queryContestSubmissionLog(QueryContestSubmissionLogBo queryContestSubmissionLogBo);

    /**
     * 移除本次比赛所有提交记录
     * @param id
     * @return
     */
    public String removeAllContestSubmissionLogbyContestId(@Param("id")Long id);
}
