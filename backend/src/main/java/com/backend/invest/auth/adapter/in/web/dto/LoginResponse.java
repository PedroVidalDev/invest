package com.backend.invest.auth.adapter.in.web.dto;

public record LoginResponse(String accessToken) {
  public static LoginResponse from(String token) {
    return new LoginResponse(token);
  }
}
