package com.backend.invest.userinstrument.domain.port.exception;

public class UserInstrumentNotFoundException extends RuntimeException {
    public UserInstrumentNotFoundException(String id) {
        super("User instrument not found: "+id);
    }
}
