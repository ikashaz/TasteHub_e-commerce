package com.e_commerce.FoodCat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:4200") // Allow requests from your Angular app
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*") // Allow all headers
                .allowCredentials(true); // Allow credentials if needed;


//        // Allow CORS for the uploads directory
//        registry.addMapping("/uploads/**")
//                .allowedOrigins("http://localhost:4200") // Allow requests from your Angular app
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}

