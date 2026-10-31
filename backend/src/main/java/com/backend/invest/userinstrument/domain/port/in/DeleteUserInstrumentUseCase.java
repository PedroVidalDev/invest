package com.backend.invest.userinstrument.domain.port.in;

public interface DeleteUserInstrumentUseCase {
    void delete(String userId, String instrumentId);
}
