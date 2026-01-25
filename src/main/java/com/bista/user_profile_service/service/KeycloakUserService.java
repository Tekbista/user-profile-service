package com.bista.user_profile_service.service;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import com.bista.user_profile_service.security.KeycloakProperties;

@Service
public class KeycloakUserService {

    private final Keycloak keycloak;
    private final KeycloakProperties keycloakProperties;

    public KeycloakUserService(Keycloak keycloak, KeycloakProperties keycloakProperties) {
        this.keycloak = keycloak;
        this.keycloakProperties = keycloakProperties;
    }

    public UserRepresentation getUser(String userId) {
        return keycloak
                .realm(keycloakProperties.getRealm())
                .users()
                .get(userId)
                .toRepresentation();
    }

}
