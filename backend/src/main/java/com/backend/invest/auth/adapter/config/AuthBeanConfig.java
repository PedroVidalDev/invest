package com.backend.invest.auth.adapter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backend.invest.auth.domain.port.out.PasswordEncoderPort;
import com.backend.invest.auth.domain.port.out.TokenProviderPort;
import com.backend.invest.auth.domain.service.AuthService;
import com.backend.invest.user.domain.port.in.UserQueryUseCase;

@Configuration
public class AuthBeanConfig {
  @Bean
  public AuthService authService(UserQueryUseCase userQuery, PasswordEncoderPort passwordEncoder,
      TokenProviderPort tokenProvider) {
    return new AuthService(userQuery, passwordEncoder, tokenProvider);
  }
}
