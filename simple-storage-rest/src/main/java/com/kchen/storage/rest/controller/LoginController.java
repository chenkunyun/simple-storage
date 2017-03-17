package com.kchen.storage.rest.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class LoginController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "home";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "redirect:";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(
            @RequestParam("user_name") String userName,
            @RequestParam("password") String password,
            HttpServletRequest request,
            HttpServletResponse response) {

        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            currentUser.logout();
        }

        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        AuthenticationException exception = null;
        try {
            currentUser.login(token);
            try {
                WebUtils.redirectToSavedRequest(request, response, "/");
            } catch (IOException e) {
                throw new AuthenticationException("failed to redirect");
            }
        } catch(AuthenticationException ae){
            exception = ae;
        }

        String viewName = null;
        if (exception != null) {
            token.clear();
            viewName = "redirect:login";
        }

        return viewName;
    }
}
