package com.dazuizui.business.aop.org;


import com.dazuizui.business.domain.OrgMember;
import org.aspectj.lang.JoinPoint;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface OrgMemberAop {
    /**
     * @author Bryan yang 22/8/2023
     *
     * 插入组织成员
     *    主要做了幂等性处理
     * @param joinpoint
     *
     * @return
     * @throws Exception
     */
    public void insertMember(JoinPoint joinpoint) throws Exception;
}
