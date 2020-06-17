package com.aochsner.helloworld;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @Value("${name:Default}")
    private String name;

    @RequestMapping("/")
    public String hello() {
        return "Hello "+name;
    }
}
