package com.kchen.storage.rest.search;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SearchApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SearchApplication.class)
                .web(true)
                .run(args);
    }
}
