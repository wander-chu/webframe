package com.example.webserver.service.impl;

import com.example.webserver.model.CacheTest.CacheTestObjectOne;
import com.example.webserver.model.CacheTest.CacheTestObjectTwo;
import com.example.webserver.service.CacheTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CacheTestServiceImpl implements CacheTestService {
    private static Logger logger = LoggerFactory.getLogger(CacheTestServiceImpl.class);
    @Override
    public CacheTestObjectOne getCacheTestObjectOne() {
        logger.info("new CacheTestObjectOne");
        return new CacheTestObjectOne("wander", 35);
    }

    @Override
    public CacheTestObjectTwo getCacheTestObjectTwo() {
        logger.info("new CacheTestObjectTwo");
        return new CacheTestObjectTwo("shandong", "jinan");
    }
}
