package com.orthoproducts.auth_service.auth.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.orthoproducts.auth_service.auth.entity.User;

import java.time.Instant;



@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserInfoResponse(

        @JsonProperty("id")
        String id,

        @JsonProperty("username")
        String username,

        @JsonProperty("email")
        String email,

        @JsonProperty("role")
        String role,

        @JsonProperty("active")
        boolean active,

        @JsonProperty("email_verified")
        boolean emailVerified,

        @JsonProperty("created_at")
        Instant createdAt

) {

    public static UserInfoResponse from(User user) {
        return new UserInfoResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.isActive(),
                user.isEmaiVerified(),
                user.getCreatedAt()
        );
    }
}