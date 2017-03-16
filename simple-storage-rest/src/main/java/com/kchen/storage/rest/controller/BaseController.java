package com.kchen.storage.rest.controller;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class BaseController {

    /**
     * handle authorization exception
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler({AuthorizationException.class})
    public ModelAndView unauthorizedException(NativeWebRequest request, AuthorizationException e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("exception", e);
        mv.setViewName("unauthorized");
        return mv;
    }
}
