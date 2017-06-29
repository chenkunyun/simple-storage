package com.kchen.storage.sidecar.php.demo;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SideCarPhpDemoApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SideCarPhpDemoApplication.class)
                .web(true)
                .run(args);
    }
}
