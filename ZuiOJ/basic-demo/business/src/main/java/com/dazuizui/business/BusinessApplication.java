package com.dazuizui.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ZuiOJ Application
 */
@SpringBootApplication
public class BusinessApplication {
    public static void main(String[] args) {
        System.out.println("Welcome to ZuiOJ ,I`m Bryan yang ");
        SpringApplication.run(BusinessApplication.class, args);
        System.out.println("ZuiOJ  starting");
        System.out.println("by https://github.com/DaZuiZui");
    }
}
