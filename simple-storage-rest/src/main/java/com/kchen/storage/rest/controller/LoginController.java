package com.kchen.storage.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kchen on 2017/2/28.
 * login stuff
 */
@RestController
@RequestMapping("/")
public class LoginController {

    @RequestMapping("/")
    public Map<String, String> home() {
        Map<String, String> result = new HashMap<>();
        result.put("code", "1");
        result.put("msg", "succ");
        return result;
    }

    @RequestMapping("/login")
    public String login(
            @RequestParam("login_name") String loginName,
            @RequestParam("password") String password) {
        return "ok";
    }
}
