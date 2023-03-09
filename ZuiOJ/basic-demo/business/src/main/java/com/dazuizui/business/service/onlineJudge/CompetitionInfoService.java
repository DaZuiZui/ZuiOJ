package com.dazuizui.business.service.onlineJudge;

import com.dazuizui.business.domain.bo.PaglingQueryContestantsInThisContestBo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public interface CompetitionInfoService {
    /**
     * 分页查询参赛选手信息在这个比赛
     * @param paglingQueryContestantsInThisContestBo
     * @return
     */
    public String paglingQueryContestantsInThisContest(PaglingQueryContestantsInThisContestBo paglingQueryContestantsInThisContestBo);

    /**
     * 查看排名，
     * todo 当初眼花了，写在这个实现接口，忙完主线在优化
     * @param contestId
     * @param page
     * @param numbers
     * @return
     */
    public String viewRanking(@RequestParam("contestId")Long contestId,Integer page,Integer numbers);
}
