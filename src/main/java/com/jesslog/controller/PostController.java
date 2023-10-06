package com.jesslog.controller;

import com.jesslog.request.PostCreate;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController // Controller 도 할 수 있는데 데이터기반으로 응답을
public class PostController {

    @PostMapping("/posts")
    public String post(@RequestBody PostCreate postCreate) {
        log.info("postCreate: ",postCreate.getContent());
        return "Hello world";
    }
}
