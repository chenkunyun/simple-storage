package com.kchen.storage.test.service.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by kchen on 2017/3/1.
 */

@Configuration
@ComponentScan(basePackages = {"com.kchen.storage.service.account"})
public class ServiceConfig {
}
