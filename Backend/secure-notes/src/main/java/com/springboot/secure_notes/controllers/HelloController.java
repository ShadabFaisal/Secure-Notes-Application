package com.springboot.secure_notes.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello";
    }

    @GetMapping("/contact")
    public String contact(){
        return "Contact Page";
    }
}
