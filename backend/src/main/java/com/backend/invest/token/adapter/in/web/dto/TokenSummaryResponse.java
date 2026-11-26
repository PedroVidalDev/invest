package com.backend.invest.token.adapter.in.web.dto;

import java.time.LocalDateTime;

import com.backend.invest.token.domain.ServiceType;
import com.backend.invest.token.domain.Token;

public record TokenSummaryResponse(
    String userId,
    ServiceType service,
    int attempts,
    LocalDateTime expiresAt
) {
    public static TokenSummaryResponse from(Token token) {
        return new TokenSummaryResponse(
            token.getUserId(),
            token.getService(),
            token.getAttempts(),
            token.getExpiresAt()
        );
    }
}
