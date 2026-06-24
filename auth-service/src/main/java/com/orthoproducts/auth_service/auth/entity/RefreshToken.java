package com.orthoproducts.auth_service.auth.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Document(collection = "refresh_tokens")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RefreshToken {

    @Id
    private String id;


    @Indexed(unique = true)
    @Field("token_hash")
    private String tokenHash;


    @Field("user_id")
    @Indexed
    private String userId;

    @Field("family_id")
    @Indexed
    private String familyId;


    @Field("revoked")
    @Builder.Default
    private boolean revoked = false;



    @Indexed(expireAfter = "0")
    @Field("expires_at")
    private Instant expiresAt;

    @CreatedDate
    @Field("created_at")
    private Instant createdAt;

    @Field("replaced_by_token_hash")
    private String replacedByTokenHash;


    public boolean isExpired(){
        return Instant.now().isAfter(this.expiresAt);
    }

    private boolean isValid(){
        return !revoked && !isExpired();
    }

}
