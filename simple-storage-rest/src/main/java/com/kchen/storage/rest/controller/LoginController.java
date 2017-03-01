package com.kchen.storage.rest.controller;

import com.kchen.storage.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private AccountService accountService;

    @RequestMapping("/")
    public Map<String, Object> home() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", accountService.login("", ""));
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
