package com.jwt.restapi.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    private static final Logger logger = LoggerFactory.getLogger(AuditorAwareImpl.class);

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            logger.info("No user is authenticated.");
            return Optional.empty();
        }

        String currentUser = authentication.getName();
        logger.info("Authenticated user: " + currentUser); // Log the authenticated user

        return Optional.ofNullable(currentUser);
    }
}
