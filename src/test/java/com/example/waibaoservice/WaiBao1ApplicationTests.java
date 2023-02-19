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
        final User user = mapper.selectUserById(1);
        System.out.println(user);
    }

}
