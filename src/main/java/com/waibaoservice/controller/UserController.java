package com.waibaoservice.controller;

import com.waibaoservice.pojo.User;
import com.waibaoservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * @author DJS
 * Date create 22:54 2023/2/19
 * Modified By DJS
 **/

@Controller
@RequestMapping("/login")
public class UserController {

    @Autowired
    UserService userService;

    public UserController() {}

    @ResponseBody
    @GetMapping("/test")
    public String test(String username) {
        System.out.println(userService);
        User user = new User();
        user.setTel(username);
        user.setPassword("fewsfadsf");
        userService.userRegister(user);
        return "";
    }

    // 用户登录接口
    @ResponseBody
    @PostMapping("/userLogin")
    public boolean login(@RequestBody User user) {
        System.out.println(user);
        return userService.loginService(user);
    }

    // 用户注册接口
    @ResponseBody
    @PostMapping("/userRegister")
    public boolean register(@RequestBody User user) {
        return userService.userRegister(user);
    }
}
