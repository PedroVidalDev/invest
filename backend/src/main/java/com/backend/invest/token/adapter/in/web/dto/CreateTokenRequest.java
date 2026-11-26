package com.backend.invest.token.adapter.in.web.dto;

import com.backend.invest.token.domain.ServiceType;
import com.backend.invest.token.domain.port.in.command.CreateTokenCommand;

public record CreateTokenRequest(
    String userId,
    ServiceType service
) {
    public CreateTokenCommand toCommand() {
        return new CreateTokenCommand(userId, service);
    }
}
