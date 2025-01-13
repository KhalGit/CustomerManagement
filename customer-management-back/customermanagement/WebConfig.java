package com.example.customermanagement;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Allow cross-origin requests from the frontend running on localhost:3000
        registry.addMapping("/**") // This applies to all API endpoints
                .allowedOrigins("http://localhost:3000"); // Allow React app on localhost:3000
    }
}