package com.backend.invest.token.adapter.in.web.dto;

import java.time.LocalDateTime;

import com.backend.invest.token.domain.ServiceType;
import com.backend.invest.token.domain.Token;

public record TokenResponse(
    String id,
    String userId,
    ServiceType service,
    LocalDateTime expiresAt,
    LocalDateTime createdAt
) {
    public static TokenResponse from(Token token) {
        return new TokenResponse(
            token.getId(),
            token.getUserId(),
            token.getService(),
            token.getExpiresAt(),
            token.getCreatedAt()
        );
    }
}
