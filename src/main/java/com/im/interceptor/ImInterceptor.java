package com.im.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ImInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        if(url.contains("/index")||url.contains("/login")||url.contains("/regis") || url.contains("/fpa")||url.contains("/sendCode")){
            return true;
        }else {
            Object obj = request.getSession().getAttribute("loginId");
            if (obj == null){
                response.sendRedirect("/index");
                return false;
            }else {
                return true;
            }
       }
    }
}
