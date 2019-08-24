package com.example.webserver.controller;

import com.example.webserver.model.CacheTest.CacheTestObjectOne;
import com.example.webserver.model.CacheTest.CacheTestObjectTwo;
import com.example.webserver.service.CacheTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "caches")
public class CacheTestController {
    @Autowired
    private CacheTestService cacheTestService;

    @GetMapping("1")
    public CacheTestObjectOne getCacheTestObjectOne() {
        return cacheTestService.getCacheTestObjectOne();
    }

    @GetMapping("2")
    public CacheTestObjectTwo getCacheTestObjectTwo() {
        return cacheTestService.getCacheTestObjectTwo();
    }
}
