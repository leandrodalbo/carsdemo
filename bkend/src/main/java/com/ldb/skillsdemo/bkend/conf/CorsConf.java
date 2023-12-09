package com.ldb.skillsdemo.bkend.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConf implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:5173/")
                .allowCredentials(false)
                .allowedMethods(HttpMethod.DELETE.name(), HttpMethod.PUT.name(), HttpMethod.POST.name(), HttpMethod.GET.name());
    }
}


