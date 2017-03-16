package com.kchen.storage.rest.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    @RequiresRoles("admin")
    public String user() {
        return "user";
    }
}
