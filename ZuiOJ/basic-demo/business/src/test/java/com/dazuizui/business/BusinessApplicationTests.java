package com.dazuizui.business;

import com.dazuizui.business.service.LanguageCommandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BusinessApplicationTests {
    @Autowired
    private LanguageCommandService languageCommandService;
    @Test
    void contextLoads() {
        System.out.println(languageCommandService.queryByLanguageId(1));
    }

}
