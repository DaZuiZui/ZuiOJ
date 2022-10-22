package com.dazuizui.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BusinessApplication {
    public static void main(String[] args) {
        System.out.println("Welcome to ZuiOJ - TLMOJ in 哈尔滨广厦学院校区");
        System.out.println("Hi This is TLM Team, we are form 哈尔滨广厦学院");
        SpringApplication.run(BusinessApplication.class, args);
        System.out.println("ZuiOJ - TLMOJ starting");
    }
}
