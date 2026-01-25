package com.bista.user_profile_service.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "keycloak")
@Data
public class KeycloakProperties {
    private String serverUrl;
    private String realm;
    private String clientId;
    private String clientSecret;
}
