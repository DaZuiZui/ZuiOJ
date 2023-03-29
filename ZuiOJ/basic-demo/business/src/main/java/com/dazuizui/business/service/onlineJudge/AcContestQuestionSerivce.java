package com.dazuizui.business.service.onlineJudge;

import com.dazuizui.basicapi.entry.AcContestQuestion;
import com.dazuizui.business.domain.bo.ElementOfQueryLogBo;
import com.dazuizui.business.domain.bo.QueryContestSubmissionLogBo;
import com.dazuizui.business.domain.bo.QueryCountByContestIdAndQuestionIdBo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 比赛题目提交业务层操作接口
 */
@Service
public interface AcContestQuestionSerivce {
    /**
     * 查询日志通过元素列表
     * @return
     */
    public String queryLogByElement(@RequestBody ElementOfQueryLogBo elementOfQueryLogBo);

    /**
     * 删除此用户的提交记录通过Id
     * @param id
     * @return
     */
    public String deleteLogById(@Param("id")Long id);

    /**
     * 提交答案
     */
    public Long submitAnswer(AcContestQuestion acContestQuestion,String status);

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

    /**
     * 通过QuestionId和ContestId查询提交数据
     * @param elementOfQueryLogBo
     * @return
     */
    public String queryCountByContestIdAndQuestionId(ElementOfQueryLogBo elementOfQueryLogBo);
}
