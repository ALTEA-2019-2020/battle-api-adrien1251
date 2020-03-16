package com.altea.battleAPI.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Configuration
public class RestConfiguration {

    private String userName;

    private String password;

    @Value("${trainers.service.username}")
    void setUserName(String userName) {
        this.userName = userName;
    }

    @Value("${trainers.service.password}")
    void setPassword(String password) {
        this.password = password;
    }

    @Bean
    @Qualifier("trainerApiRest")
    RestTemplate trainerApiRestTemplate(){
        ClientHttpRequestInterceptor clientHttpRequestInterceptor = new BasicAuthenticationInterceptor(userName, password);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Arrays.asList(clientHttpRequestInterceptor));

        return restTemplate;
    }

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}