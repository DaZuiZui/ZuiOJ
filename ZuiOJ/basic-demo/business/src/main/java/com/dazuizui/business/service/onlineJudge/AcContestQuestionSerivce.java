package com.dazuizui.business.service.onlineJudge;

import com.dazuizui.basicapi.entry.AcContestQuestion;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.bo.CheckTheSubmitQuesitonDetailInfoBo;
import com.dazuizui.business.domain.bo.ElementOfQueryLogBo;
import com.dazuizui.business.domain.bo.QueryContestSubmissionLogBo;
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
     * 此业务用于websokcet OJ数据大屏的提交日志的数据交互使用
     * @param contestId 比赛id
     * @return
     */
    public ResponseVo querySubmitLogByContestIdOrderUpdateTimeDesc(@Param("contestId")Long contestId);

    /**
     * @author Bryan Yang y51288033@outlook.com
     * 查看详细信息通过user_id contest_id
     * @param checkTheDetailInfoBo
     * @return
     */
    public String checkTheDetailInfoByUserIdAndContestId(CheckTheSubmitQuesitonDetailInfoBo checkTheDetailInfoBo);

    /**
     * @author Bryan Yang(Dazui) 07/08/2022 22:00:00 PM
     *
     * 查看提交日志
     * query commition log
     *
     * 业务层
     *    通过比赛id和用户名
     *
     * Business
     *    query log by contestId and username
     *
     * @param elementOfQueryLogBo 查询元素实体
     * @return String
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
     * @author Bryan Yang(Dazui) 07/08/2022 22:00:00 PM
     *
     * 查看本体提交日志
     * query commition log
     *
     * 业务层
     *    通过比赛id查询提交日志
     *
     * Business
     *    Query submission logs through competition ID
     *
     * @param queryContestSubmissionLogBo 查询元素实体
     * @return String
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

    /**
     * @author Bryan Yang(Dazui) 06/08/2022 22:00:00 PM
     *
     * 通过比赛id还有问题id查询提交日志
     * query log by contestId and questionId
     *
     * @param elementOfQueryLogBo 查询元素实体
     * @return
     */
    public String queryLogByContestIdAndQuestionId(ElementOfQueryLogBo elementOfQueryLogBo);
}
