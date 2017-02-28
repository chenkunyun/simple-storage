package com.kchen.storage.rest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by kchen on 2017/2/26.
 * Main class
 */

@SpringBootApplication
@MapperScan(basePackages = {"com.kchen.storage.dao.mapper.common"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
