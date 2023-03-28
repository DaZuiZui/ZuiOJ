package com.dazuizui.business.aop.onlineJudge;

import com.dazuizui.business.domain.bo.FilterQueryMatchSaveCodeBo;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 比赛时候的用户提交的代码Aop接口
 */
@Component
public interface SubmmitionCodeInContestAop {
    /**
     * 筛选查询比赛提交保存的代码
     *      筛选查询数据，主要做了身份验证，查看是否符合权限查看
     * @param joinpoint
     * @return
     */
    public String filterQueryMatchSaveCode(JoinPoint joinpoint) throws Exception;
}
