package com.example.webserver.service;

import com.example.webserver.model.CacheTest.CacheTestObjectOne;
import com.example.webserver.model.CacheTest.CacheTestObjectTwo;

public interface CacheTestService {
    CacheTestObjectOne getCacheTestObjectOne();
    CacheTestObjectTwo getCacheTestObjectTwo();
}