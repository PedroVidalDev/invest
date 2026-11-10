package com.backend.invest.token.domain;

import java.time.LocalDateTime;

public class Token {
  private String id;
  private String userId;
  private ServiceType service;
  private String code;
  private int attempts;
  private LocalDateTime expiresAt;
  private LocalDateTime createdAt;

  public Token(String id, String userId, ServiceType service, String code, int attempts,
      LocalDateTime expiresAt, LocalDateTime createdAt) {
    this.id = id;
    this.userId = userId;
    this.service = service;
    this.code = code;
    this.attempts = attempts;
    this.expiresAt = expiresAt;
    this.createdAt = createdAt;
  }

  public String getId() {
    return this.id;
  }

  public String getUserId() {
    return this.userId;
  }

  public ServiceType getService() {
    return this.service;
  }

  public String getCode() {
    return this.code;
  }

  public int getAttempts() {
    return this.attempts;
  }

  public LocalDateTime getExpiresAt() {
    return this.expiresAt;
  }

  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }

  // Behavior Setters
  public void incrementAttempts() {
    this.attempts++;
  }
}
