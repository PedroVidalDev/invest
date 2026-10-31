package com.backend.invest.userinstrument.adapter.in.web.dto;

import com.backend.invest.userinstrument.domain.UserInstrument;

public record UserInstrumentSummaryResponse (
    String id,
    String userId,
    String instrumentId
){
    public static UserInstrumentSummaryResponse from(UserInstrument userInstrument) {
        return new UserInstrumentSummaryResponse (
            userInstrument.getId(),
            userInstrument.getUserId(),
            userInstrument.getInstrumentId()
        );
    }
}