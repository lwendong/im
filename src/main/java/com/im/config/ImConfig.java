package com.im.config;

import com.im.interceptor.ImInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ImConfig implements WebMvcConfigurer {

    @Autowired
    private ImInterceptor imInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(imInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/view/**","/js/**","/css/**");
    }

}
