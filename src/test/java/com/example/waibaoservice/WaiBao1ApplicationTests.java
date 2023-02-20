package com.example.waibaoservice;

import com.waibaoservice.mapper.UserMapper;
import com.waibaoservice.pojo.User;
import com.waibaoservice.utils.MapperUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = WaiBao1ApplicationTests.class)
class WaiBao1ApplicationTests {
    @Test
    void contextLoads() {
        final UserMapper mapper = MapperUtils.getMapper(UserMapper.class);
        final User user_by_id = mapper.selectUserById(1);
        final User user_by_tel = mapper.selectUserByTel("2312312");
        System.out.println(user_by_id);
        System.out.println(user_by_tel);
    }

}
