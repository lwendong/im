package com.im.Utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ImWebUtils {

    public static HttpServletRequest getRequset(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }

    public static HttpServletResponse getResponse(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getResponse();
    }

    public static HttpSession getSession(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes.getRequest().getSession();
    }

    public static Object getSessionAttribute(String key){
        HttpSession session = getSession();
        return session.getAttribute(key);
    }

    public static void setSessionAttribute(String key,Object value){
        HttpSession session = getSession();
        session.setAttribute(key,value);
    }

    public static void removeSessionAttribute(String key){
        HttpSession session = getSession();
        session.removeAttribute(key);
    }
}
