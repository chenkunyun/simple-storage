package com.kchen.storage.rest.consumer.controller;

import com.kchen.storage.rest.consumer.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    @Autowired
    private RestService restService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public Integer add(@RequestParam("a") Integer a, @RequestParam("b") Integer b) {
        return restService.add(a, b);
    }
}
