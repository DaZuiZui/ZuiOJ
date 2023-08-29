package com.dazuizui.business.aop.onlineJudge;

import com.dazuizui.business.domain.bo.DuplicateCodeBo;
import com.dazuizui.business.domain.bo.FilterQueryMatchSaveCodeBo;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 比赛时候的用户提交的代码Aop接口
 */
@Component
public interface SubmmitionCodeInContestAop {
    /**
     * @author Bryan Yang(Dazui) 30 8/2023
     *
     * 获取涉嫌重复的代码
     *    主要做了鉴权是否为管理员
     * @param joinpoint
     * @return
     */
    public void getDuplicateCode(JoinPoint joinpoint);

    /**
     * 筛选查询比赛提交保存的代码
     *      筛选查询数据，主要做了身份验证，查看是否符合权限查看
     * @param joinpoint
     * @return
     */
    public String filterQueryMatchSaveCode(JoinPoint joinpoint) throws Exception;

    /**
     * 通过id查询代码详细信息
     *      筛选查询数据，主要做了身份验证，查看是否符合权限查看
     * @param joinpoint
     * @return
     */
    public String findOneById(JoinPoint joinpoint) throws Exception;

    /**
     * 通过提交记录id删除记录
     * @param joinpoint
     * @return
     */
    public String deleteById(JoinPoint joinpoint) throws Exception;
}
