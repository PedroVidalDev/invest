package com.backend.invest.token.domain.exception;

public class TokenAlreadyExistsException extends RuntimeException {
    public TokenAlreadyExistsException(String userId) {
        super("Token already exists for user: " + userId);
    }
}
