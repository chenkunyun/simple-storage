package com.kchen.storage.rest.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String info() {
        return "info";
    }
}
