package com.backend.invest.token.domain.exception;

public class TokenNotFoundException extends RuntimeException {
    public TokenNotFoundException(String userId) {
        super("Token not found for user: " + userId);
    }
}
