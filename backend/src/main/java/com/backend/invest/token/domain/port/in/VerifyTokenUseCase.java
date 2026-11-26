package com.backend.invest.token.domain.port.in;

import com.backend.invest.token.domain.ServiceType;

public interface VerifyTokenUseCase {
  void verifyToken(String userId, ServiceType service, String code);
}
