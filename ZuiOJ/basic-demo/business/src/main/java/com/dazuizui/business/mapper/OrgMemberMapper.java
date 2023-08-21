package com.dazuizui.business.mapper;

import com.dazuizui.business.domain.OrgMember;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrgMemberMapper {

    /**
     * @author Bryan yang 2023 21/8/2023
     * 插入成员信息
     * @param orgMember
     * @return
     */
    public Long insertMember(OrgMember orgMember);
}
