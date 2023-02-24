package com.waibaoservice.service.impl;

import com.waibaoservice.mapper.UserMapper;
import com.waibaoservice.pojo.User;
import com.waibaoservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author DJS
 * Date create 23:01 2023/2/19
 * Modified By DJS
 **/

// 加上该注解添加到spring容器, 实现依赖输入
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    public UserServiceImpl() {

    }

    // 登录
    @Override
    public boolean loginService(User user) {
        System.out.println("loginService call");
        User u = mapper.selectUserByInfo(user);
        return u != null;
    }

    // 注册
    @Override
    public boolean userRegister(User user) {
        System.out.println("userRegister call");
        String tel = user.getTel();
        User u = mapper.selectUserByTel(tel);
        if (u == null) {
            int result = mapper.insertUser(user);
            return result == 1;
        }
        else return false;
    }

}
