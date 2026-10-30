package com.backend.invest.userinstrument.domain.port.exception;

public class UserInstrumentAlreadyExistsException extends RuntimeException {
    public UserInstrumentAlreadyExistsException(String id) {
        super("Already exists instrument register: "+id);
    }
}
