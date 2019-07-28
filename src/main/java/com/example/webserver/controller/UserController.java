package com.example.webserver.controller;

import com.example.webserver.model.User;
import com.example.webserver.model.dao.UserDao;
import com.example.webserver.model.vo.UserVo;
import com.example.webserver.service.UserService;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "User", description = "User Operation")
@RestController
@RequestMapping(value = "users")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "get users", notes = "get users")
    @GetMapping("")
    public ResponseEntity<List<User>> getUsers() {
        List<UserDao> userDaos = userService.getUsers();
        List<User> users = Lists.newArrayList();
        for (UserDao userDao : userDaos) {
            User user = new User();
            user.setId(userDao.getId());
            user.setName(userDao.getUsername());
            user.setAge(userDao.getAge());
            users.add(user);
        }
        return ResponseEntity.ok(users);
    }

    @ApiOperation(value = "get user", notes = "get user")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "编号", required = true, paramType = "path")
    })
    @GetMapping(value = "{id}")
    public ResponseEntity<User> getUser(@PathVariable(value = "id") Long id) {
        UserDao userDao = userService.findUserById(id);
        User user = null;
        if (userDao != null) {
            user = new User();
            user.setId(userDao.getId());
            user.setName(userDao.getUsername());
            user.setAge(userDao.getAge());
        }
        return ResponseEntity.ok(user);
    }

    @ApiOperation(value = "add user", notes = "add user")
    @PostMapping
    public ResponseEntity<Long> addUser(@RequestBody UserVo userVo) {
        UserDao user = new UserDao();
        user.setUsername(userVo.getName());
        user.setAge(userVo.getAge());
        user.setPassword("123");
        userService.save(user);
        return ResponseEntity.ok(user.getId());
    }
}
