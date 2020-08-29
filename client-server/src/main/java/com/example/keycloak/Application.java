package com.example.keycloak;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableScheduling
public class Application {

    private final Logger log = LoggerFactory.getLogger(Application.class);
    private final WebClient webClient;

    public Application(WebClient webClient) {
        this.webClient = webClient;
    }

    @Scheduled(fixedDelay = 1000)
    public void callRestrictedApi() {
        final String url = "http://localhost:8080/v1/restricted";
        log.info("Calling Restricted API=[{}]", url);
        webClient.get().uri(url).retrieve().bodyToMono(Void.class).block();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}