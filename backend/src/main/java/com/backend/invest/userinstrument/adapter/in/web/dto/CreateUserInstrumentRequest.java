package com.backend.invest.userinstrument.adapter.in.web.dto;

import com.backend.invest.userinstrument.domain.port.in.command.CreateUserInstrumentCommand;

public record CreateUserInstrumentRequest (
    String userId,
    String instrumentId
){
    public CreateUserInstrumentCommand toCommand() {
        return new CreateUserInstrumentCommand(userId, instrumentId);
    }
}
