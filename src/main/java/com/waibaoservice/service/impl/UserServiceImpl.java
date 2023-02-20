package com.waibaoservice.service.impl;

import com.waibaoservice.mapper.UserMapper;
import com.waibaoservice.pojo.User;
import com.waibaoservice.service.UserService;
import com.waibaoservice.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * @author DJS
 * Date create 23:01 2023/2/19
 * Modified By DJS
 **/

// 加上该注解添加到spring容器, 实现依赖输入
@Component
public class UserServiceImpl implements UserService {

    private final static UserMapper mapper = MapperUtils.getMapper(UserMapper.class);

    @Override
    public boolean loginService(User user) {
        User u = mapper.selectUserByInfo(user);
        return u != null;
    }
}
