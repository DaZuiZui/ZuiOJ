package com.dazuizui.business.mapper;

import com.dazuizui.business.domain.Coverage;
import com.dazuizui.business.domain.UserMaxCoverageRate;
import com.dazuizui.business.domain.bo.FindByContestIdAndQuestionIdAndMasterOdBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CoverageMapper {
    /**
     * 插入查重日日志
     * @param coverage
     * @return
     */
    public Long insertCoverageData(Coverage coverage);

    /**
     * 通过比赛id和题目id查询
     * @param contestId
     * @param questionId
     * @return
     */
    public Coverage findByContestIdAndQuestionid(@Param("contestId")Long contestId,@Param("questionId")Long questionId);

    /**
     * 通过比赛id和题目id查询
     * @param contestId     比赛id
     * @param questionId    题目id
     * @return
     */
    public Long deleteByContestIdAndQuestionid(@Param("contestId")Long contestId,@Param("questionId")Long questionId);

    /**
     * find the manx coverage rate by contest id and question id and master id;
     *
     * @param contestId
     * @param masterId
     * @return
     */
    public List<UserMaxCoverageRate> getUserMaxCoverageRateByContestIdAndMasterId(
            @Param("contestId")Long contestId, @Param("masterId")Long masterId);

    /**
     *
     * @return
     */
    public List<Coverage> findByContestIdAndQuestionIdAndMasterOd(FindByContestIdAndQuestionIdAndMasterOdBo findByContestIdAndQuestionIdAndMasterOdBo);
}
