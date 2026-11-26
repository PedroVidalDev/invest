package com.backend.invest.token.domain.port.out;

import java.util.Optional;
import com.backend.invest.token.domain.Token;

public interface TokenRepositoryPort {
  Token save (Token token);

  Optional<Token> findByUserId(String userid);

  void delete (String id);
}
