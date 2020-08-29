package com.example.keycloak.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests -> authorizeRequests
                /**
                 * A JWT that is issued from an OAuth 2.0 Authorization Server will typically either have a scope or
                 * scp attribute, indicating the scopes (or authorities) it’s been granted, for example:
                 *
                 * { "scope" : "user:read" }
                 *
                 * When this is the case, Resource Server will attempt to coerce these scopes into a list of granted
                 * authorities, prefixing each scope with the string "SCOPE_".
                 *
                 * @see https://docs.spring.io/spring-security/site/docs/current/reference/html5/#oauth2resourceserver-jwt-authorization
                 */
                .mvcMatchers(HttpMethod.GET, "/v1/restricted").hasAuthority("SCOPE_user:read")
        ).oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }

}
