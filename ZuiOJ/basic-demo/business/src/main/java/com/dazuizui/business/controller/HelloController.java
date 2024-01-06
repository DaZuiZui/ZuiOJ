package com.dazuizui.business.controller;

import com.dazuizui.business.domain.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hello")
public class HelloController {
    @PostMapping("/hello")
    public String hello(@RequestBody User user){
        System.err.println(user);
        return "Hi this is Dazui Bryan ,";
    }
}
