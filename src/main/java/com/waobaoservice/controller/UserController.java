package com.waobaoservice.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DJS
 * Date create 19:07 2023/2/16
 * Modified By DJS
 **/

@RestController
public class UserController {

    @RequestMapping("/index")
    public String test(String username) {
        System.out.println(username);
        return "index";
    }

}
