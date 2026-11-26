package com.backend.invest.token.domain.exception;

public class TokenExpiredException extends RuntimeException {
    public TokenExpiredException(String userId) {
        super("Token expired for user: " + userId);
    }
}
