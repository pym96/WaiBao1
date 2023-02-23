package com.waibaoservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.waibaoservice.mapper")
public class WaiBao1Application {
    public static void main(String[] args) {
        SpringApplication.run(WaiBao1Application.class, args);
    }
}
