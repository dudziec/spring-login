package com.example.emailsender.domain;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    
    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello world!";
    }

    @GetMapping("/private")
    public String privateAccess() {
        return "private access";
    }
}
