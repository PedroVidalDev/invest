package com.backend.invest.token.domain.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import com.backend.invest.token.domain.Token;
import com.backend.invest.token.domain.exception.InvalidTokenException;
import com.backend.invest.token.domain.exception.TokenAttemptsExceededException;
import com.backend.invest.token.domain.exception.TokenExpiredException;
import com.backend.invest.token.domain.exception.TokenNotFoundException;
import com.backend.invest.token.domain.port.in.CreateTokenUseCase;
import com.backend.invest.token.domain.port.in.TokenQueryUseCase;
import com.backend.invest.token.domain.port.in.VerifyTokenUseCase;
import com.backend.invest.token.domain.port.in.command.CreateTokenCommand;
import com.backend.invest.token.domain.port.out.TokenRepositoryPort;

public class TokenService implements CreateTokenUseCase, TokenQueryUseCase, VerifyTokenUseCase {
  private static final int MAX_ATTEMPTS = 5;

  private final TokenRepositoryPort repository;

  public TokenService(TokenRepositoryPort repository) {
    this.repository = repository;
  }

  @Override
  public Token create(CreateTokenCommand command) {
    repository.findByUserId(command.userId())
        .ifPresent(existing -> repository.delete(existing.getId()));

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
  public void verifyToken(String userId, String code) {
    Token token = findByUserId(userId);

    if (LocalDateTime.now().isAfter(token.getExpiresAt())) {
      repository.delete(token.getId());
      throw new TokenExpiredException(userId);
    }

    if (!token.getCode().equals(code)) {
      token.incrementAttempts();

      if (token.getAttempts() >= MAX_ATTEMPTS) {
        repository.delete(token.getId());
        throw new TokenAttemptsExceededException(userId);
      }

      repository.save(token);
      throw new InvalidTokenException(userId);
    }

    repository.delete(token.getId());
  }

  @Override
  public Token findByUserId(String userId) {
    return repository.findByUserId(userId).orElseThrow(() -> new TokenNotFoundException(userId));
  }
}
