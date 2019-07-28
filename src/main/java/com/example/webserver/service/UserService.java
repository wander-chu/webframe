package com.example.webserver.service;

import com.example.webserver.model.dao.UserDao;

import java.util.List;

public interface UserService {
    List<UserDao> getUsers();

    UserDao findUserById(long id);

    void save(UserDao user);

    void edit(UserDao user);

    void delete(long id);
}