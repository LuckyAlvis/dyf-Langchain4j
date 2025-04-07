package com.shenzhen.dai.langchain4j.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 你好
 * @author: daiyifan
 * @create: 2025-04-07 11:24
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

}
