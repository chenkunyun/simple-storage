package com.kchen.storage.rest.controller;

import com.kchen.storage.dao.domain.common.Account;
import com.kchen.storage.dao.mapper.common.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kchen on 2017/2/28.
 */
@RestController
@RequestMapping("/")
public class LoginController {

    @Autowired
    private AccountMapper accountMapper;

    @RequestMapping("/")
    public String home() {
        Account account = accountMapper.selectByPrimaryKey(Long.valueOf(1));
        System.out.println(account);
        return "home";
    }

    @RequestMapping("/login")
    public String login(
            @RequestParam("login_name") String loginName,
            @RequestParam("password") String password) {
        return "ok";
    }
}
