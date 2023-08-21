package com.dazuizui.business.controller;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.StatusCodeMessage;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.OrgMember;
import com.dazuizui.business.service.org.OrgMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 组织成员控制器
 */
@RestController
@RequestMapping("/org")
public class OrgMemberController {

    @Autowired
    private OrgMemberService orgMemberService;

    /**
     * @author Bryan yang 2023 21/8/2023
     * 插入成员信息
     * @param orgMember
     * @return
     */
    @PostMapping("/insertmenber")
    public String insertMember(@RequestBody OrgMember orgMember){
        if (orgMember == null){
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.IsNull,null, StatusCode.IsNull));
        }

        return orgMemberService.insertMember(orgMember);
    }
}
