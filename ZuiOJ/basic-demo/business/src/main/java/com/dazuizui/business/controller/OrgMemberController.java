package com.dazuizui.business.controller;

import com.alibaba.fastjson2.JSONArray;
import com.dazuizui.basicapi.entry.StatusCode;
import com.dazuizui.basicapi.entry.StatusCodeMessage;
import com.dazuizui.basicapi.entry.vo.ResponseVo;
import com.dazuizui.business.domain.OrgMember;
import com.dazuizui.business.service.org.OrgMemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @ApiOperation("添加组织成员")
    @PostMapping("/insertmenber")
    public String insertMember(@RequestParam("nonPowerToken")String nonPowerToken ,@RequestBody OrgMember orgMember){
        if (orgMember == null){
            return JSONArray.toJSONString(new ResponseVo<>(StatusCodeMessage.IsNull,null, StatusCode.IsNull));
        }

        return orgMemberService.insertMember(orgMember);
    }
}
