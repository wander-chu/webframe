package com.example.webserver.cache;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class MemoryCache implements Cache {
    private ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<>();

    @Override
    public void save(String key, Object value) {
        concurrentHashMap.put(key, value);
    }

    @Override
    public Object get(String key) {
        return concurrentHashMap.get(key);
    }
}
