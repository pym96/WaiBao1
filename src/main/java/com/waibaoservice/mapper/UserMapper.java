package com.waibaoservice.mapper;

import com.waibaoservice.pojo.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMapper {
    User selectUserById(int id);
    User selectUserByInfo(User user);
    User selectUserByTel(String tel);

    // 插入
    int insertUser(User user);
}
