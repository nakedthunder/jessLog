package com.jesslog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Controller 도 할 수 있는데 데이터기반으로 응답을
public class PostController {

    @GetMapping("/posts")
    public String get() {
        return "Hello world";
    }
}
