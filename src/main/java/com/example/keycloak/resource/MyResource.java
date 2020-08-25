package com.example.keycloak.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyResource {

    @GetMapping("/v1/allowed")
    public String allowedToken() {
        return "Token in allowed";
    }

    @GetMapping("/v1/not-allowed")
    public String deniedToken() {
        return "Token is not allowed";
    }

}