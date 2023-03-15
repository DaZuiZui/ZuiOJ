package com.dazuizui.business.aop.onlineJudge;

import com.dazuizui.business.domain.bo.AdminAddCompetitionInfoBo;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 比赛选手Aop接口
 */
@Component
public interface CompetitionInfoAop {

    /**
     * 管理员插入比赛选手信息
     *     主要负责管理员权限的鉴别和幂等性
     * @return
     */
    public String adminAddCompetitionInfo(JoinPoint joinpoint) throws Exception;

    /**
     * 分页获取比赛选手数据
     * 鉴权查看是否为管理员
     * @return
     */
    public String paglingQueryContestantsInThisContest(JoinPoint joinpoint) throws Exception;
}
