package com.backend.invest.userpreference.domain.exception;

public class UserPreferenceAlreadyExistsException extends RuntimeException {
    public UserPreferenceAlreadyExistsException(String id) {
        super("Already exists preferences for user: "+id);
    }
}
