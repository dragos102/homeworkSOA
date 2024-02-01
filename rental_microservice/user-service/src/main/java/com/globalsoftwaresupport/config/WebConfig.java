package com.globalsoftwaresupport.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/v1")
//                .allowedOrigins("*")
//                .allowedMethods("GET", "POST", "PUT", "DELETE");
//
//        registry.addMapping("/v1/user/")
//                .allowedOrigins("*")
//                .allowedMethods("GET", "POST", "PUT", "DELETE");
//    }
}
