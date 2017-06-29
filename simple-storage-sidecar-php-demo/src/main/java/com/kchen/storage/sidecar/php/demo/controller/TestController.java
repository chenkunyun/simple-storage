package com.kchen.storage.sidecar.php.demo.controller;

import com.kchen.storage.sidecar.php.demo.model.HealthCheckModel;
import com.kchen.storage.sidecar.php.demo.model.UserModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {

    @RequestMapping("/health")
    public HealthCheckModel healthCheck() {
        return new HealthCheckModel();
    }

    @RequestMapping("/service")
    public UserModel userModel() {
        return new UserModel();
    }
}
