package com.dazuizui.business.service.onlineJudge;

import com.dazuizui.basicapi.entry.CompetitionInfo;
import com.dazuizui.business.domain.bo.AdminAddCompetitionInfoBo;
import com.dazuizui.business.domain.bo.PaglingQueryContestantsInThisContestBo;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public interface CompetitionInfoService {
    /**
     * 检查选手是否有参赛数据
     * @param ContestId
     * @param id
     * @return
     */
    public CompetitionInfo checkForEntryByContestIdAnd(Long ContestId, Long id);

    /**
     * 管理员插入比赛选手信息
     * @return
     */
    public String adminAddCompetitionInfo(@RequestBody AdminAddCompetitionInfoBo adminAddCompetitionInfoBo);

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
