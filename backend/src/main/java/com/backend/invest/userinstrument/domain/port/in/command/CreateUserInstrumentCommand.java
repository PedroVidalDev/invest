package com.backend.invest.userinstrument.domain.port.in.command;

public record CreateUserInstrumentCommand(
    String userId,
    String instrumentId
) {}
