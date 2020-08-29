package com.example.keycloak;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class MyTask {

    private final RestTemplate restTemplate;

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    public MyTask(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Scheduled(fixedDelay = 1000)
    public void makeRequest() {
        OAuth2AuthorizedClient keycloak = authorizedClientService.loadAuthorizedClient("keycloak", "my-app");
        final String url = "http://localhost:8080/v1/restricted";
        log.info("Calling {}", url);

        restTemplate.getForEntity(url, Void.class);
    }

}