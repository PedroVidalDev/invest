package com.backend.invest.auth.domain.port.out;

import com.backend.invest.user.domain.User;

public interface TokenProviderPort {
  String generateToken(User user);

  String validateAndGetUserId(String token);
}
