package com.backend.invest.user.domain.exception;

public class UserInactiveException extends RuntimeException {
    public UserInactiveException(String id) {
        super("User is inactive: " + id);
    }
}
