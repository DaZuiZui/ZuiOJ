package com.dazuizui.business.controller;

import com.dazuizui.basicapi.entry.bo.GetArticleByIdBo;
import com.dazuizui.business.service.admin.AttributeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Api(value = "属性表",tags = {"属性表"})
@RequestMapping("/attribute")
@RestController
public class AttributeController {
    @Autowired
    private AttributeService attributeService;

    /**
     * 获取指定表数据行数
     * @return
     */
    @ApiOperation("获取指定表的数据行数")
    @PostMapping("/getNumberOfQuestionAnswer")
    public String getNumberOfTable(@RequestParam("table") Long table){
        return attributeService.getNumberOfTable(table);
    }


}
