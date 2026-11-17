package com.backend.invest.token.adapter.out.persistence;

import java.time.LocalDateTime;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.backend.invest.token.domain.ServiceType;
import com.backend.invest.token.domain.Token;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Token")
@Table(name = "tb_tokens")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TokenJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String userId;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private ServiceType service;

    private String code;
    private int attempts;
    private LocalDateTime expiresAt;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    public static TokenJpaEntity from(Token token) {
        return new TokenJpaEntity(
                token.getId(),
                token.getUserId(),
                token.getService(),
                token.getCode(),
                token.getAttempts(),
                token.getExpiresAt(),
                token.getCreatedAt()
        );
    }

    public Token toDomain() {
        return new Token(
                id,
                userId,
                service,
                code,
                attempts,
                expiresAt,
                createdAt
        );
    }
}
