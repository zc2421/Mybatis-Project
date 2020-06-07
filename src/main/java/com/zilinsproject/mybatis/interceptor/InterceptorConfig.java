package com.zilinsproject.mybatis.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * @author zilinsmac
 */

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    private static final List<String> EXCLUDE_PATH= Arrays.asList("/user/index", "/user/login", "/user/getCode", "/user/register",
                                                                "/status/**",
                                                                "/product/**", "/");

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserLoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(EXCLUDE_PATH);
    }
}
