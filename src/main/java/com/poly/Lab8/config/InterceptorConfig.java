package com.poly.Lab8.config;

import com.poly.Lab8.interceptor.AuthInterceptor;
import com.poly.Lab8.interceptor.LogInterceptor; // Import thêm
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    AuthInterceptor authInterceptor;

    @Autowired
    LogInterceptor logInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Cấu hình AuthInterceptor (Bài 5)
        registry.addInterceptor(authInterceptor)
                .addPathPatterns(
                        "/admin/**",
                        "/account/change-password",
                        "/account/edit-profile",
                        "/order/**"
                )
                .excludePathPatterns(
                        "/admin/home/index"
                );

        // Cấu hình LogInterceptor (Bài 6 - Bước 2)
        // Lọc các URI giống hệt như AuthInterceptor
        registry.addInterceptor(logInterceptor)
                .addPathPatterns(
                        "/admin/**",
                        "/account/change-password",
                        "/account/edit-profile",
                        "/order/**"
                )
                .excludePathPatterns(
                        "/admin/home/index"
                );
    }
}