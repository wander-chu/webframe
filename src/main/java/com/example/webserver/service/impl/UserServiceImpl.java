package com.example.webserver.service.impl;

import com.example.webserver.model.dao.UserDao;
import com.example.webserver.repository.UserRepository;
import com.example.webserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDao> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDao findUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void save(UserDao user) {
        userRepository.save(user);
    }

    @Override
    public void edit(UserDao user) {
        userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }
}
