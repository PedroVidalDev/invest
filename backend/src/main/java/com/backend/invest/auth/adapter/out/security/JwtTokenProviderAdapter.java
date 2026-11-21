package com.backend.invest.auth.adapter.out.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.backend.invest.auth.domain.port.out.TokenProviderPort;
import com.backend.invest.user.domain.User;

@Component
public class JwtTokenProviderAdapter implements TokenProviderPort {
  private final String secret;

  public JwtTokenProviderAdapter(@Value("${jwt.secret}") String secret) {
    this.secret = secret;
  }

  @Override
  public String generateToken(User user) {
    Algorithm algorithm = Algorithm.HMAC256(secret);

    return JWT.create().withIssuer("invest-api").withSubject(user.getId())
        .withClaim("email", user.getEmail()).withClaim("category", user.getCategory().name())
        .withExpiresAt(Instant.now().plus(24, ChronoUnit.HOURS)).sign(algorithm);
  }
}
