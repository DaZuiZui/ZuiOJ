package com.dazuizui.business.controller;

import com.dazuizui.basicapi.entry.LanguageCommand;
import com.dazuizui.business.service.onlineJudge.LanguageCommandService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/LanguageCommand")
@Api(value = "语言命令参数控制器",tags = {"语言命令参数控制器"})
public class LanguageCommandController {
    @Autowired
    private LanguageCommandService languageCommandService;

    /**
     * 根据语言id去查询
     * Query by language id
     * 
     * @param languageId
     * @return
     */
    @GetMapping("/readByLanguageId/{languageId}")
    public List<LanguageCommand> queryByLanguageId(@PathVariable int languageId){
       return languageCommandService.queryByLanguageId(languageId);
    }
}
