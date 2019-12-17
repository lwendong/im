package com.im.Utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CookieUtils {

    //默认最大时间 7 天
    private static final int COOKIE_MAX_AGE = 604800;
    private static final int COOKIE_DEFAULT_AGE = 1800;

    @Autowired
    private HttpServletRequest request;

    private static HttpServletRequest stateRequest;

    @Autowired
    private HttpServletResponse response;

    private static HttpServletResponse ststeResponse;

    @PostConstruct
    public void init(){
        stateRequest = request;
        ststeResponse = response;
    }


    /**
     * 根据Cookie名称得到Cookie对象，不存在该对象则返回Null
     */
    public static Cookie getCookie(String name) {
        Cookie[] cookies = stateRequest.getCookies();
        if (cookies == null||cookies.length<1) {
            return null;
        }
        Cookie cookie = null;
        for (Cookie c : cookies) {
            if (name.equals(c.getName())) {
                cookie = c;
                break;
            }
        }
        return cookie;
    }

    /**
     * 根据Cookie名称直接得到Cookie值
     */
    public static String getCookieValue(String name) {
        Cookie cookie = getCookie(name);
        if(cookie != null){
            return cookie.getValue();
        }
        return null;
    }

    /**
     * 删除cookie
     */
    public static void removeCookie(String name) {
        if (null == name) {
            return;
        }
        Cookie cookie = getCookie(name);
        if(null != cookie){
            cookie.setPath("/");
            cookie.setValue("");
            cookie.setMaxAge(0);
            ststeResponse.addCookie(cookie);
        }
    }

    /**
     * 添加一条新的Cookie，可以指定过期时间(单位：秒)
     */
    public static void setCookie(String name,String value, int maxValue) {
        if (StringUtils.isBlank(name)) {
            return;
        }
        if (null == value) {
            value = "";
        }
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        if (maxValue != 0) {
            cookie.setMaxAge(maxValue);
        } else {
            cookie.setMaxAge(COOKIE_MAX_AGE);
        }
        ststeResponse.addCookie(cookie);
        try {
            ststeResponse.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加一条新的Cookie，默认30分钟过期时间
     */
    public static void setCookie(String name,String value) {
        setCookie(name, value, COOKIE_DEFAULT_AGE);
    }

}
