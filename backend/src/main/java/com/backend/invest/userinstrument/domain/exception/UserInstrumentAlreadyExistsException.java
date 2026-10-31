package com.backend.invest.userinstrument.domain.exception;

public class UserInstrumentAlreadyExistsException extends RuntimeException {
    public UserInstrumentAlreadyExistsException(String userId, String instrumentId) {
        super("Instrument " + instrumentId + " already in watchlist of user: " + userId);
    }
}
