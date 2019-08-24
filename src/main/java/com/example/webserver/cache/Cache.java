package com.example.webserver.cache;

public interface Cache {
    void save(String key, Object value);
    Object get(String key);
}
