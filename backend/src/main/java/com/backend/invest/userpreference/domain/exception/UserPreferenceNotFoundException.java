package com.backend.invest.userpreference.domain.exception;

public class UserPreferenceNotFoundException extends RuntimeException {
    public UserPreferenceNotFoundException(String id) {
        super("Preferences user not found: " + id);
    }
}
