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

/**
 * Created by kchen on 2017/2/28.
 * login stuff
 */
@Controller
@RequestMapping("/")
public class LoginController {

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
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
            try {
                WebUtils.redirectToSavedRequest(request, response, "/user");
            } catch (IOException e) {
                throw new AuthenticationException("failed to redirect");
            }
        }catch(UnknownAccountException uae){

        }catch(IncorrectCredentialsException ice){

        }catch(LockedAccountException lae){

        }catch(ExcessiveAttemptsException eae){

        }catch(AuthenticationException ae){

        }

        if(currentUser.isAuthenticated()){
            return null;
        }else{
            token.clear();
            return "redirect:login";
        }
    }
}
