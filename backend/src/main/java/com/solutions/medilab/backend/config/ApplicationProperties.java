package com.solutions.medilab.backend.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
@Setter
@Getter
public class ApplicationProperties {

    private String authenticationSecret;

    private Integer accessTokenExpirationDuration;

    private Integer refreshTokenExpirationDuration;

}
