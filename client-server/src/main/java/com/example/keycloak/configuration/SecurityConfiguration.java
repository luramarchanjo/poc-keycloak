package com.example.keycloak.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

@Configuration
public class SecurityConfiguration {

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

}
