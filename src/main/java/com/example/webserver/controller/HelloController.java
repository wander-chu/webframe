package com.example.webserver.controller;

import com.example.webserver.logger.LoggerMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController extends BaseController {
    @RequestMapping("hello")
    @LoggerMessage(description = "aop logger test")
    public String hello(@RequestParam(value = "msg") String helloMessage) {
        return helloMessage;
    }
}
