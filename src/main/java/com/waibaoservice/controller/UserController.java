package com.waibaoservice.controller;

import com.waibaoservice.pojo.User;
import com.waibaoservice.pojo.User;
import com.waibaoservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author DJS
 * Date create 22:54 2023/2/19
 * Modified By DJS
 **/

@RestController
@RequestMapping("/login")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController (UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/userLogin")
    public boolean login(@RequestBody User user) {
        System.out.println(user);
        return userService.loginService(user);
    }
}
