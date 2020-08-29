package com.example.keycloak.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        // Do any additional configuration here
//        restTemplateBuilder.additionalMessageConverters(new FormHttpMessageConverter(), new OAuth2AccessTokenResponseHttpMessageConverter());
//        restTemplateBuilder.errorHandler(new OAuth2ErrorResponseErrorHandler());

        return restTemplateBuilder.build();
    }

}