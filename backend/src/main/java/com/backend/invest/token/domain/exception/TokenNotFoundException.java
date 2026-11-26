package com.backend.invest.token.domain.exception;

import com.backend.invest.token.domain.ServiceType;

public class TokenNotFoundException extends RuntimeException {
    public TokenNotFoundException(String userId) {
        super("Token not found for user: " + userId);
    }

    public TokenNotFoundException(String userId, ServiceType service) {
        super("Token of type " + service + " not found for user: " + userId);
    }
}
