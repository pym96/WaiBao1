package com.waibaoservice.service.impl;

import com.waibaoservice.mapper.UserMapper;
import com.waibaoservice.pojo.User;
import com.waibaoservice.service.UserService;
import com.waibaoservice.utils.MapperUtils;
import jakarta.annotation.Resource;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author DJS
 * Date create 23:01 2023/2/19
 * Modified By DJS
 **/

// 加上该注解添加到spring容器, 实现依赖输入
@Component
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserMapper mapper;

    public UserServiceImpl() {}

    // 登录
    @Override
    public boolean loginService(User user) {
        User u = mapper.selectUserByInfo(user);
        return u != null;
    }

    // 注册
    @Override
    public boolean userRegister(User user) {
        String tel = user.getTel();
        User u = mapper.selectUserByTel(tel);
        if (u == null) {
            int result = mapper.insertUser(user);
            return result == 1;
        }
        else return false;
    }

}
