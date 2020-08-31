package com.example.keycloak.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

    /**
     * The OAuth2AuthorizedClientManager is responsible for the overall management of OAuth2AuthorizedClient(s).
     * <p>
     * Reference https://docs.spring.io/spring-security/site/docs/current/reference/html5/#customizing-the-access-token-response-3
     * Reference https://docs.spring.io/spring-security/site/docs/current/reference/html5/#oauth2Client-authorized-manager-provider
     */
    @Bean
    public OAuth2AuthorizedClientManager authorizedClientManager(
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientService authorizedClientService
    ) {

        final OAuth2AuthorizedClientProvider authorizedClientProvider = OAuth2AuthorizedClientProviderBuilder.builder()
                .clientCredentials() // Configures support for the client_credentials grant type.
                .refreshToken() // Configures support for the refresh_token.
                .build();

        final AuthorizedClientServiceOAuth2AuthorizedClientManager authorizedClientManager
                = new AuthorizedClientServiceOAuth2AuthorizedClientManager(clientRegistrationRepository, authorizedClientService);

        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

        return authorizedClientManager;
    }

    /**
     * The OAuth 2.0 Client support integrates with WebClient using an ExchangeFilterFunction.
     * <p>
     * Reference https://docs.spring.io/spring-security/site/docs/current/reference/html5/#oauth2Client-webclient-servlet
     */
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