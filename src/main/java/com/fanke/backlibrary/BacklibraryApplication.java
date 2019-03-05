package com.fanke.backlibrary;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@MapperScan("com.fanke.backlibrary.mapper") //@MapperScan用户扫描mybatis的mapper接口，生成代理
public class BacklibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(BacklibraryApplication.class, args);
    }

}
