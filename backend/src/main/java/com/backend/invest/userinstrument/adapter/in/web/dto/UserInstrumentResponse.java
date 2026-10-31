package com.backend.invest.userinstrument.adapter.in.web.dto;

import com.backend.invest.userinstrument.domain.UserInstrument;

public record UserInstrumentResponse (
    String id,
    String userId,
    String instrumentId
) {
    public static UserInstrumentResponse from(UserInstrument userInstrument) {
        return new UserInstrumentResponse (
            userInstrument.getId(),
            userInstrument.getUserId(),
            userInstrument.getInstrumentId()
        );
    }
}