package com.waobaoservice.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author DJS
 * Date create 19:07 2023/2/16
 * Modified By DJS
 **/

@Controller
public class UserController {

    @RequestMapping("/hello")
    public String test(String username) {
        System.out.println(username);
        return "";
    }

}
