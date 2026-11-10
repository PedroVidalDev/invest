package com.backend.invest.token.domain.port.in;

public interface VerifyTokenUseCase {
  void verifyToken(String userId, String code);
}
