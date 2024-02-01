package com.gpmonaco.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import util.JwtUtil;

@Configuration
@EnableScheduling
public class GlobalConfiguration {
    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }

}
