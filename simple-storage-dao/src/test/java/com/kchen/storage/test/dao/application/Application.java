package com.kchen.storage.test.dao.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan(basePackages = {"com.kchen.storage.dao.mapper.common"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}