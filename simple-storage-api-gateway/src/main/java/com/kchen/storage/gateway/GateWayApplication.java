package com.kchen.storage.gateway;


import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringCloudApplication
public class GateWayApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(GateWayApplication.class)
                .web(true)
                .run(args);
    }
}
