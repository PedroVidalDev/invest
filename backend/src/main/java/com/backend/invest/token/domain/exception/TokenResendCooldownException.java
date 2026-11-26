package com.backend.invest.token.domain.exception;

public class TokenResendCooldownException extends RuntimeException {
    public TokenResendCooldownException(String userId, long secondsRemaining) {
        super("Token resend blocked for user: " + userId
                + ". Try again in " + secondsRemaining + " seconds");
    }
}
