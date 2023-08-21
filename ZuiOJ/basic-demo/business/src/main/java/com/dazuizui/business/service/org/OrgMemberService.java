package com.dazuizui.business.service.org;

import com.dazuizui.business.domain.OrgMember;
import org.springframework.stereotype.Service;

/**
 * @author Bryan yang 2023 21/8/2023
 * 该业务主要对组织成员管理进行操作
 */
public interface OrgMemberService {

    /**
     * @author Bryan yang 2023 21/8/2023
     * 插入成员信息
     * @param orgMember
     * @return
     */
    public String insertMember(OrgMember orgMember);
}
