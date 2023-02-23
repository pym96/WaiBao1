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
    public String test(String username) {
        System.out.println(userService);
        User user = new User();
        user.setTel(username);
        user.setPassword("fewsfadsf");
        userService.userRegister(user);
        System.out.println("insert");
        return "";
    }

    // 用户登录接口
    @PostMapping("/userLogin")
    public boolean login(@RequestBody User user) {
        System.out.println(user);
        return userService.loginService(user);
    }

    // 用户注册接口
    @PostMapping("/userRegister")
    public boolean register(@RequestBody User user) {
        return userService.userRegister(user);
    }
}
