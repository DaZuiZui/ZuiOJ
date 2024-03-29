package com.dazuizui.business.aop.onlineJudge;

import com.dazuizui.business.domain.bo.AdminAddCompetitionInfoBo;
import com.dazuizui.business.domain.bo.DeleteAllCompetitionInfoByContestIdBo;
import com.dazuizui.business.domain.bo.DeleteTheCompetitionByIdBo;
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
     * 通过id删除比赛选手aop
     *      主要负责管理员身份的鉴权
     * @param joinpoint
     * @return
     */
    public String deleteTheCompetitionById(JoinPoint joinpoint) throws Exception;
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

    /**
     * 删除所有比赛选手通过比赛Id
     *      主要负责管理员的身份验证
     * @Param deleteAllCompetitionInfoByContestIdBo
     */
    public String deleteAllCompetitionInfoByContestId(JoinPoint joinpoint) throws Exception;
}
