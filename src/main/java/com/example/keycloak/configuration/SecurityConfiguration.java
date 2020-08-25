package com.example.keycloak.configuration;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public JwtDecoder jwtDecoder(OAuth2ResourceServerProperties oAuth2ResourceServerProperties) {
        return NimbusJwtDecoder.withJwkSetUri(oAuth2ResourceServerProperties.getJwt().getJwkSetUri()).build();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests -> authorizeRequests
                /**
                 * When this is the case, Resource Server will attempt to coerce these scopes into a list of granted
                 * authorities, prefixing each scope with the string "SCOPE_".
                 *
                 * @see https://docs.spring.io/spring-security/site/docs/current/reference/html5/#oauth2resourceserver-jwt-authorization
                 */
                .mvcMatchers(HttpMethod.GET, "/v1/restricted").hasAuthority("SCOPE_user:read")
        ).oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }

}
