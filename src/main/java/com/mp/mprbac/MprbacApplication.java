package com.mp.mprbac;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mp.mprbac.mapper")
public class MprbacApplication {

    public static void main(String[] args) {
        SpringApplication.run(MprbacApplication.class, args);
    }

}
