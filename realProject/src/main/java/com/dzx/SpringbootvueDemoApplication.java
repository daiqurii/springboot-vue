package com.dzx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dzx.mapper")
public class SpringbootvueDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootvueDemoApplication.class, args);
    }

}
