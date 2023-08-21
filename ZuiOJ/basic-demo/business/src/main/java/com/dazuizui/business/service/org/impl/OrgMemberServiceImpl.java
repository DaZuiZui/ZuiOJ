package com.dazuizui.business.service.org.impl;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.StatusCodeMessage;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.OrgMember;
import com.dazuizui.business.mapper.OrgMemberMapper;
import com.dazuizui.business.service.org.OrgMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Bryan yang 2023 21/8/2023
 * 该业务主要对组织成员管理进行操作
 */
@Service
public class OrgMemberServiceImpl implements OrgMemberService {

    @Autowired
    private OrgMemberMapper orgMemberMapper;

    /**
     * @author Bryan yang 2023 21/8/2023
     * 插入成员信息
     * @param orgMember
     * @return
     */
    @Override
    public String insertMember(OrgMember orgMember){
        Long numbersOfOption = orgMemberMapper.insertMember(orgMember);

        if (numbersOfOption.longValue() == 0){
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.Error,null, StatusCode.Error));
        }

        return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.OK,null, StatusCode.OK));
    }
}
