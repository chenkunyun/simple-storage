package com.kchen.storage.common.framework.filter;


import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ky.chen on 2016/10/18.
 * 登录认证filter
 */

public class AuthFilter implements Filter {

    private static final String[] WHITE_URL = {"/**/static/**","/**/auth/**","/**/test/**","/**/assets/**","/**/chatClient/**/","/**/downloadserver/**/"};

    private static AntPathMatcher matcher	= new AntPathMatcher();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rep = (HttpServletResponse) response;
        String url = req.getServletPath();

        if(urlMatch(url, WHITE_URL)){
            chain.doFilter(request, response);
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    private boolean urlMatch(String url,String[] patterns){
        if (url == null || patterns == null)
            return false;

        for (String pattern : patterns)
        {
            if (matcher.match(pattern, url))
                return true;
        }
        return false;
    }
}
