package com.waibaoservice.controller;

import com.waibaoservice.pojo.User;
import com.waibaoservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * @author DJS
 * Date create 22:54 2023/2/19
 * Modified By DJS
 **/

@RestController
@RequestMapping("/login")
public class UserController {

    @Autowired
    UserService userService;

    public UserController() {}

    @GetMapping("/test")
    public String test() {
        System.out.println("test call");
        return "test";
    }

    // 用户登录接口
    @PostMapping("/userLogin")
    public boolean login(@RequestBody User user) {
        System.out.println("login call");
        return userService.loginService(user);
    }

    // 用户注册接口
    @PostMapping("/userRegister")
    public boolean register(@RequestBody User user) {
        System.out.println("register call");
        return userService.userRegister(user);
    }
}
