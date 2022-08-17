package com.xw.goodscenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xw.goodscenter.mapper")
public class GoodsCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoodsCenterApplication.class, args);
    }

}
