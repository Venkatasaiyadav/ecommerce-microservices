package com.orthoproducts.auth_service.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Document(collection = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {


    private String id;


    @Field("username")
    @Indexed(unique = true, sparse = true)
    private String username;

    @Field("email")
    @Indexed(unique = true, sparse = true)
    private String email;

    @JsonIgnore
    @Field("password")
    private String password;


    @Field("role")
    private String role;



    @Field("active")
    @Builder.Default
    private boolean active=true;



    @Field("email_verified")
    @Builder.Default
    private boolean emaiVerified=false;


    @Field("failed_login_attempts")
    @Builder.Default
    private int failedLoginAttempts=0;


    @Field("locked_until")
    private Instant lockedUntil;


    @CreatedDate
    @Field("created_at")
    private Instant createdAt;

    @Field("updated_at")
    @LastModifiedDate
    private Instant updatedAt;


    public boolean isAccountLocked(){
        if(lockedUntil==null){
            return false;
        }
        return Instant.now().isBefore(lockedUntil);
    }


    public boolean incrementFailedAttempts() {
        this.failedLoginAttempts++;
        // Lock after 5 consecutive failures for 15 minutes
        if (this.failedLoginAttempts >= 5) {
            this.lockedUntil = Instant.now().plusSeconds(900);
            return true;
        }
        return false;
    }

    /**
     * Resets failed login counter and clears lock on successful authentication.
     */
    public void resetFailedAttempts() {
        this.failedLoginAttempts = 0;
        this.lockedUntil = null;
    }


}
