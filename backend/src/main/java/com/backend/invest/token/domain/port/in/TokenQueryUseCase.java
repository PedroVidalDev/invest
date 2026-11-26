package com.backend.invest.token.domain.port.in;

import com.backend.invest.token.domain.Token;

public interface TokenQueryUseCase {
  Token findByUserId (String id);
}
