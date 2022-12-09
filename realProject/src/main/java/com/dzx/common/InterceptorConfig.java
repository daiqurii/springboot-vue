package com.dzx.common;

import com.dzx.interceptor.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * TODO
 *
 * @author Daiquiri
 * @version 1.0
 * @date 2022/12/9 0:25
 */

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())
                //拦截  业务功能的所有页面都需要检查JWT
                .addPathPatterns("/user/**")
                //放行  登录和注册的页面不检查JWT
                .excludePathPatterns("/login/**");
    }
}
