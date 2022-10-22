package com.dazuizui.business.Initializer;

import com.dazuizui.basicapi.entry.LanguageCommand;
import com.dazuizui.business.mapper.LanguageCommandMapper;
import com.dazuizui.business.mapper.ProgrammingLanguageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author Bryan yang
 * 数据初始化
 */
@Component
public class InitializerApplication {
    @Autowired
    private ProgrammingLanguageMapper programmingLanguageMapper;

    /**
     * 语言数据初始化
     */
    @PostConstruct
    public void initializerLanguageData(){
        //获取全部语言

        System.out.println(programmingLanguageMapper.getAllLanguage());
    }
}
