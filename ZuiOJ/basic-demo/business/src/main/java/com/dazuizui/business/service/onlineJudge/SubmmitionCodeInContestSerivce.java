package com.dazuizui.business.service.onlineJudge;

import com.dazuizui.business.domain.bo.FilterQueryMatchSaveCodeBo;
import org.springframework.stereotype.Service;

@Service
public interface SubmmitionCodeInContestSerivce {
    /**
     * 通过比赛id还有题目id还有用户id指定提交代码数据业务接口
     * @param findByContestIdAndQuestionIdAndUserIdBo
     * @return
     */
    public String filterQueryMatchSaveCode( FilterQueryMatchSaveCodeBo findByContestIdAndQuestionIdAndUserIdBo);
}
