package com.wxy.springbackend.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class test {
    test() {
        System.out.println("testcontroller created!");
    }


    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot!";
    }
}
