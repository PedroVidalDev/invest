package com.backend.invest.userinstrument.domain.exception;

public class UserInstrumentNotFoundException extends RuntimeException {
    public UserInstrumentNotFoundException(String userId, String instrumentId) {
        super("Instrument " + instrumentId + " not found in watchlist of user: " + userId);
    }
}
