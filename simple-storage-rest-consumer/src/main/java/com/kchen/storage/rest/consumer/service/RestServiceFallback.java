package com.kchen.storage.rest.consumer.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class RestServiceFallback implements RestService{
    @Override
    public Integer add(@RequestParam("a") Integer a, @RequestParam("b") Integer b) {
        return -999999999;
    }
}
