package com.urfread.breaknews.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //所有路径都经过拦截器，但排除 /user 下的路径
        registry.addInterceptor(jwtInterceptor)
                .excludePathPatterns("/user/**")
                .excludePathPatterns("/image/view/**");
    }
}
