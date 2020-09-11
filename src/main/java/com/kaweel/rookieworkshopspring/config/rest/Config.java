package com.kaweel.rookieworkshopspring.config.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class Config {

    @Value("${rest.timeout}")
    private int timeout;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Bean
    public RestTemplate restTemplate() {
        Duration timeOut = Duration.ofSeconds(timeout);
        return restTemplateBuilder
                .errorHandler(new CustomResponseErrorHandler())
                .setConnectTimeout(timeOut)
                .setReadTimeout(timeOut)
                .interceptors(new CustomClientHttpRequestInterceptor())
                .build();
    }

}
