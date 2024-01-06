package com.dazuizui.online_judge.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hi")
public class HelloController {
    @GetMapping("/hi")
    public String hi(@RequestParam("id")Long id){
        return "This is Bryan yang hello"+id;
    }
}
