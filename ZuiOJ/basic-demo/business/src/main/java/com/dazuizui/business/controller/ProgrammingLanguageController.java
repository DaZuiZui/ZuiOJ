package com.dazuizui.business.controller;

import com.dazuizui.basicapi.entry.bo.ProgramBo;
import com.dazuizui.business.service.onlineJudge.ProgrammingLanguageService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bryan yang(杨易达)，Gang Liu(刘港)
 * 编程语言控制器
 *      该控制器主要调用业务层实现对编程语言表的增删改查的业务实现
 *
 * Programming language controller
 *         The controller mainly calls the business layer to realize the business
 *         implementation of adding, deleting, modifying and checking the programming
 *         language table
 */
@RestController
@RequestMapping("/ProgrammingLanguage")
@Api(value = "编程语言控制器",tags = {"编程语言控制器"})
public class ProgrammingLanguageController {
    @Autowired
    private ProgrammingLanguageService programmingLanguageService;

    @PostMapping("/addProgrammingLanguage")
    public long addProgrammingLanguageTable(@RequestBody ProgramBo programBo){
        return programmingLanguageService.addProgrammingLanguageTable(programBo);
    } 
}
