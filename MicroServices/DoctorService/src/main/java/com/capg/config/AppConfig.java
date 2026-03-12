package com.capg.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    @LoadBalanced // shorthand method to use service name instead of url
    public RestTemplate restTemplate(){
        return new RestTemplate() ;
    }

}