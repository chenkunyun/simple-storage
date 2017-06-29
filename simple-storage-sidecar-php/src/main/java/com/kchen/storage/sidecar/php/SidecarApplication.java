package com.kchen.storage.sidecar.php;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;


@SpringBootApplication
@EnableSidecar
public class SidecarApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SidecarApplication.class)
                .run(args);
    }
}
