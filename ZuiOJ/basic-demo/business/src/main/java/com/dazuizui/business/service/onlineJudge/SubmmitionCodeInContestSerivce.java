package com.dazuizui.business.service.onlineJudge;

import com.dazuizui.business.domain.CodeInContest;
import com.dazuizui.business.domain.bo.FilterQueryMatchSaveCodeBo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public interface SubmmitionCodeInContestSerivce {
    /**
     * 通过比赛id还有题目id还有用户id指定提交代码数据业务接口
     * @param findByContestIdAndQuestionIdAndUserIdBo
     * @return
     */
    public String filterQueryMatchSaveCode( FilterQueryMatchSaveCodeBo findByContestIdAndQuestionIdAndUserIdBo);

    /**
     * 通过id查询代码详细信息
     */
    public String findOneById(@Param("id")Long id);


    /**
     * 通过提交记录id删除记录
     * @param contestId
     * @return
     */
    public String deleteByContestId(Long contestId);

    /**
     * 通过提交记录id删除记录
     * @param id  mongoDb主键id
     * @return
     */
    public String deleteById(String id);
}
