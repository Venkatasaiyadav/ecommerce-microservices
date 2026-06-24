package com.orthoproducts.auth_service.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Application-level bean configuration.
 *
 * Architectural Decisions:
 *
 * 1. UserDetailsService as lambda: Spring Security requires this interface
 *    for authentication. We load by email since email is our login identifier.
 *    Throwing UsernameNotFoundException triggers Spring Security's
 *    BadCredentialsException flow (prevents user enumeration).
 *
 * 2. BCrypt strength 12: OWASP recommends BCrypt with cost factor 10-12.
 *    12 provides ~250ms hashing time on modern hardware - slow enough to
 *    deter brute force, fast enough for good UX.
 *
 * 3. DaoAuthenticationProvider: Spring's built-in provider that ties
 *    UserDetailsService + PasswordEncoder together. No need to
 *    reimplement the comparison logic ourse
 *
 *
 *
 *
 *
 *
 *
 *
 *    lves.
 *
 * 4. MongoTransactionManager: Enables multi-document ACID transactions
 *    in MongoDB (requires replica set). Critical for refresh token
 *    rotation where we must atomically revoke old + create new token.
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return email -> userRepository.findByEmail(email)
                .map(user -> org.springframework.security.core.userdetails.User
                        .withUsername(user.getEmail())
                        .password(user.getPassword())
                        .roles(user.getRole().name())
                        .accountLocked(user.isAccountLocked())
                        .disabled(!user.isActive())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User not found with email: " + email
                ));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config
    ) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * MongoTransactionManager enables multi-document transactions.
     * Required for atomic refresh token rotation.
     * Note: MongoDB replica set is required for transactions.
     * For local dev with single node, start mongod with --replSet rs0.
     */
    @Bean
    public MongoTransactionManager transactionManager(
            MongoDatabaseFactory dbFactory
    ) {
        return new MongoTransactionManager(dbFactory);
    }
}