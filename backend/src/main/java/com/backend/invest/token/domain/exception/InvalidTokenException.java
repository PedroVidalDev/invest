package com.backend.invest.token.domain.exception;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String userId) {
        super("Invalid token code for user: " + userId);
    }
}
