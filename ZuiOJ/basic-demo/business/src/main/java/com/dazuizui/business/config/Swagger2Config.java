package com.dazuizui.business.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class Swagger2Config {
    private static ApiInfo DEFAULT = null;
    @Bean
    public Docket docket(){
        Contact DEFAULT_CONTACT = new Contact("ZuiOJ","www.dazuizui.com/hblog/","y51288033@outlook.com");
        DEFAULT = new ApiInfo(
                "ZuiOJ System",
                "ZuiOJ System swagger",
                "V-1.0",
                "\"www.dazuizui.com/hblog/",
                DEFAULT_CONTACT,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT);
    }
}