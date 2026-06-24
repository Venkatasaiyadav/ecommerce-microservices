package com.orthoproducts.auth_service.auth.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.orthoproducts.common.enums.UserRole;

public record TokenValidationResponse(

        @JsonProperty("valid")
        boolean valid,

        @JsonProperty("user_id")
        String userId,

        @JsonProperty("username")
        String username,

        @JsonProperty("email")
        String email,

        @JsonProperty("role")
        UserRole role

){

    public static TokenValidationResponse invalid(){
        return new TokenValidationResponse(false, null, null, null, null);
    }

    public static TokenValidationResponse valid(
            String userId,
            String username,
            String email,
            UserRole role
    ) {
        return new TokenValidationResponse(
                true,
                userId,
                username,
                email,
                role
        );
    }
}
