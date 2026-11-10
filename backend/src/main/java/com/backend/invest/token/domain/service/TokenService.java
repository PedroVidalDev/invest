package com.backend.invest.token.domain.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import com.backend.invest.token.domain.Token;
import com.backend.invest.token.domain.exception.TokenAlreadyExistsException;
import com.backend.invest.token.domain.exception.TokenNotFoundException;
import com.backend.invest.token.domain.port.in.CreateTokenUseCase;
import com.backend.invest.token.domain.port.in.TokenQueryUseCase;
import com.backend.invest.token.domain.port.in.command.CreateTokenCommand;
import com.backend.invest.token.domain.port.out.TokenRepositoryPort;

public class TokenService implements CreateTokenUseCase, TokenQueryUseCase {
  private final TokenRepositoryPort repository;

  public TokenService( TokenRepositoryPort repository ) {
    this.repository = repository;
  }

  @Override
  public Token create (CreateTokenCommand command) {
    if (repository.findByUserId(command.userId()).isPresent()) {
      throw new TokenAlreadyExistsException(command.userId());
    }

    SecureRandom random = new SecureRandom();
    String code = String.format("%06d", random.nextInt(1_000_000));

    Token newToken = new Token(
      null, 

      command.userId(), 
      command.service(),

      code, 
      0,

      LocalDateTime.now().plusMinutes(15), 
      LocalDateTime.now()
    );

    return repository.save(newToken);
  }

  @Override
  public Token findByUserId(String userId) {
    return repository.findByUserId(userId)
      .orElseThrow(() -> new TokenNotFoundException(userId));
  }
}
