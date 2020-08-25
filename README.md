# Overview

This is a proof of concept (POC) using [Keycloak](https://www.keycloak.org/) and [Spring](https://spring.io/) as 
Resource Server.

# Setup

1º Install [Docker](https://docs.docker.com/get-docker/)

2º After install Docker, we need to install [Keycloak](https://www.keycloak.org/), to do that, run the command below:

`docker run -d -p 18080:8080 --name=keycloak -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin quay.io/keycloak/keycloak`

# Testing

# Be Happy
