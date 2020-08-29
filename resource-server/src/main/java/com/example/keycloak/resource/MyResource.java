package com.example.keycloak.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyResource {

    private final Logger log = LoggerFactory.getLogger(MyResource.class);

    @GetMapping("/v1/restricted")
    public String allowedToken() {
        log.info("Restricted API");
        return "Restricted API";
    }

    @GetMapping("/v1/not-restricted")
    public String deniedToken() {
        log.info("Not Restricted API");
        return "Not Restricted API";
    }

}