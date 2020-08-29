package com.example.keycloak.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

    @Bean
    public WebClient webClient(OAuth2AuthorizedClientManager authorizedClientManager) {
        ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2Client =
                new ServletOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);

        /**
         * On the application.properties you should register a keycloak client, e.g:
         *
         * spring.security.oauth2.client.registration.keycloak.client-id=my-app
         * spring.security.oauth2.client.registration.keycloak.client-secret=653af962-ec99-4763-83d2-4c1223b8f037
         */
        oauth2Client.setDefaultClientRegistrationId("keycloak");

        return WebClient.builder()
                .apply(oauth2Client.oauth2Configuration())
                .build();
    }

}