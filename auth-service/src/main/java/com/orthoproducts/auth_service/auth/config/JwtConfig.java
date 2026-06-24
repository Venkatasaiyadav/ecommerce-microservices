package com.orthoproducts.auth_service.auth.config;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Getter
@Setter
@Validated

public class JwtConfig {



    @NotBlank(message = "JWT secret must not be blank")
    @Size(min = 64, message = "JWT secret must be at least 64 hex characters (256 bits for HS256)")
    private String secret;

    @Min(value = 60000, message = "Access token expiration must be at least 60 seconds")
    private long accessTokenExpiration;

    @Min(value = 3600000, message = "Refresh token expiration must be at least 1 hour")
    private long refreshTokenExpiration;

    @NotBlank(message = "JWT issuer must not be blank")
    private String issuer;
}
