package com.orthoproducts.auth_service.auth.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AuthResponse(

        @JsonProperty("access_token")
        String accessToken,

        @JsonProperty("refresh_token")
        String refreshToken,

        @JsonProperty("token_type")
        String tokenType,

        @JsonProperty("expires_in")
        long accessTokenExpiresIn,

        @JsonProperty("user")
        UserInfoResponse user

) {

    public static AuthResponse of(
            String accessToken,
            String refreshToken,
            String tokenType,
            long accessTokenExpiresIn,
            UserInfoResponse user) {

        return new AuthResponse(
                accessToken,
                refreshToken,
                tokenType,
                accessTokenExpiresIn,
                user
        );
    }
}

