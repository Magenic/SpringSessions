package com.magenic.springboot.finals.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef ="auditorProvider")
public class JPAAuditingConfiguration {

	@Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAware<String>() {
            @Override
            public Optional<String> getCurrentAuditor() {
                if (SecurityContextHolder.getContext().getAuthentication() != null) {
                    OAuth2Authentication auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
                    Object principal = auth.getUserAuthentication().getPrincipal();
                    User userDetails = (User) principal;
                    return Optional.ofNullable(userDetails.getUsername());
                } else {
                    return Optional.ofNullable("Unknown");
                }
            }
        };
    }

}
