package com.waibaoservice.service;

import com.waibaoservice.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DJS
 * Date create 22:54 2023/2/19
 * Modified By DJS
 **/
public class UserService {

    private final User user;

    @Autowired
    public UserService(User user){this.user = user;}






}
