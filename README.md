# Overview

This is a proof of concept (POC) using [Keycloak](https://www.keycloak.org/) and [Spring](https://spring.io/) as 
Resource Server and Client.

# Setup

1º Install [Docker](https://docs.docker.com/get-docker/)

2º After install Docker, we need to install [Keycloak](https://www.keycloak.org/), to do that, run the command below:

`docker run -d -p 18080:8080 --name=keycloak -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin quay.io/keycloak/keycloak`

# Testing

1º Start the **Keycloak** running the command above:

`docker start keycloak`

2º Start the **Resource Server** running the command above:

`cd resource-server && mvn clean spring-boot:run`

3º Start the **Client Server** running the command above:

`cd client-server && mvn clean spring-boot:run`

### Postman

If you want to teste the **Resource Server** using [Postman](https://www.postman.com/) as Client Server there is a 
**collection** and **environment** to it!

```text
src/
    main/
        resources/
                postman/
```

# Be Happy
