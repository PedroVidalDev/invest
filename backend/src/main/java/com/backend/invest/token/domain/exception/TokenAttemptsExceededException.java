package com.backend.invest.token.domain.exception;

public class TokenAttemptsExceededException extends RuntimeException {
    public TokenAttemptsExceededException(String userId) {
        super("Maximum token attempts exceeded for user: " + userId);
    }
}
