package com.orthoproducts.auth_service.auth.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(

        @NotBlank(message = "Email is required")
        @Size(max = 255, message = "Email must not exceed 255 characters")
        @JsonProperty("email")
        String email,

        @NotBlank(message = "Password is required")
        @Size(max = 128, message = "Password must not exceed 128 characters")
        @JsonProperty("password")
        String password

) {}