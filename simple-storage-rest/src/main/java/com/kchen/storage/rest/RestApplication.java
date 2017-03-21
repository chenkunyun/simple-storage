package com.kchen.storage.rest;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class RestApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(RestApplication.class).web(true).run(args);
    }
}
