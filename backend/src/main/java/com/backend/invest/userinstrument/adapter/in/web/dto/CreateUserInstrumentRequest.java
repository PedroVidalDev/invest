package com.backend.invest.userinstrument.adapter.in.web.dto;

import com.backend.invest.userinstrument.domain.UserInstrument;

public record CreateUserInstrumentRequest (
    String id,
    String userId,
    String instrumentId
){
    public UserInstrument toDomain() {
        return new UserInstrument (
            id,
            userId,
            instrumentId,
            null,
            null
        );
    }
}
