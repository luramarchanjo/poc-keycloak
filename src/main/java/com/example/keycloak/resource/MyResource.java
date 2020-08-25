package com.example.keycloak.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyResource {

    @GetMapping("/v1/restricted")
    public String allowedToken() {
        return "Restricted API";
    }

    @GetMapping("/v1/not-restricted")
    public String deniedToken() {
        return "Not Restricted API";
    }

}