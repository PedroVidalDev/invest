package com.backend.invest.token.domain.service;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDateTime;
import com.backend.invest.token.domain.ServiceType;
import com.backend.invest.token.domain.Token;
import com.backend.invest.token.domain.exception.InvalidTokenException;
import com.backend.invest.token.domain.exception.TokenAttemptsExceededException;
import com.backend.invest.token.domain.exception.TokenExpiredException;
import com.backend.invest.token.domain.exception.TokenNotFoundException;
import com.backend.invest.token.domain.exception.TokenResendCooldownException;
import com.backend.invest.token.domain.port.in.CreateTokenUseCase;
import com.backend.invest.token.domain.port.in.TokenQueryUseCase;
import com.backend.invest.token.domain.port.in.VerifyTokenUseCase;
import com.backend.invest.token.domain.port.in.command.CreateTokenCommand;
import com.backend.invest.token.domain.port.out.TokenRepositoryPort;

public class TokenService implements CreateTokenUseCase, TokenQueryUseCase, VerifyTokenUseCase {
  private static final int MAX_ATTEMPTS = 5;
  private static final int EXPIRATION_MINUTES = 15;
  private static final int RESEND_COOLDOWN_MINUTES = 3;
  private static final SecureRandom RANDOM = new SecureRandom();

  private final TokenRepositoryPort repository;

  public TokenService(TokenRepositoryPort repository) {
    this.repository = repository;
  }

  /**
   * Creates a new token for the user. Any existing token (of any type) is replaced,
   * so this also covers "resend" and "recreate after expiration".
   * A new token can only be issued after the resend cooldown, otherwise resending
   * would reset the attempts counter and allow brute forcing the code.
   */
  @Override
  public Token create(CreateTokenCommand command) {
    LocalDateTime now = LocalDateTime.now();

    repository.findByUserId(command.userId()).ifPresent(existing -> {
      LocalDateTime resendAllowedAt = existing.getCreatedAt().plusMinutes(RESEND_COOLDOWN_MINUTES);

      if (now.isBefore(resendAllowedAt)) {
        long secondsRemaining = Duration.between(now, resendAllowedAt).toSeconds() + 1;
        throw new TokenResendCooldownException(command.userId(), secondsRemaining);
      }

      repository.delete(existing.getId());
    });

    String code = String.format("%06d", RANDOM.nextInt(1_000_000));

    Token newToken = new Token(
      null,

      command.userId(),
      command.service(),

      code,
      0,

      now.plusMinutes(EXPIRATION_MINUTES),
      now
    );

    return repository.save(newToken);
  }

  @Override
  public void verifyToken(String userId, ServiceType service, String code) {
    Token token = findByUserId(userId);

    if (token.getService() != service) {
      throw new TokenNotFoundException(userId, service);
    }

    if (LocalDateTime.now().isAfter(token.getExpiresAt())) {
      repository.delete(token.getId());
      throw new TokenExpiredException(userId);
    }

    // A blocked token is kept (not deleted) so the resend cooldown still applies to it.
    if (token.getAttempts() >= MAX_ATTEMPTS) {
      throw new TokenAttemptsExceededException(userId);
    }

    if (!token.getCode().equals(code)) {
      token.incrementAttempts();
      repository.save(token);

      if (token.getAttempts() >= MAX_ATTEMPTS) {
        throw new TokenAttemptsExceededException(userId);
      }

      throw new InvalidTokenException(userId);
    }

    repository.delete(token.getId());
  }

  @Override
  public Token findByUserId(String userId) {
    return repository.findByUserId(userId).orElseThrow(() -> new TokenNotFoundException(userId));
  }
}
