package com.backend.invest.auth.domain.service;

import com.backend.invest.auth.domain.exception.InvalidCredentialsException;
import com.backend.invest.auth.domain.port.in.LoginUseCase;
import com.backend.invest.auth.domain.port.in.command.LoginCommand;
import com.backend.invest.auth.domain.port.out.PasswordEncoderPort;
import com.backend.invest.auth.domain.port.out.TokenProviderPort;
import com.backend.invest.user.domain.User;
import com.backend.invest.user.domain.exception.UserInactiveException;
import com.backend.invest.user.domain.exception.UserNotFoundException;
import com.backend.invest.user.domain.port.in.UserQueryUseCase;

public class AuthService implements LoginUseCase {
  private final UserQueryUseCase userQuery;
  private final PasswordEncoderPort passwordEncoder;
  private final TokenProviderPort tokenProvider;

  public AuthService(UserQueryUseCase userQuery, PasswordEncoderPort passwordEncoder,
      TokenProviderPort tokenProvider) {
    this.userQuery = userQuery;
    this.passwordEncoder = passwordEncoder;
    this.tokenProvider = tokenProvider;
  }

  @Override
  public String login(LoginCommand command) {
    User user;
    try {
      user = userQuery.findByEmail(command.email());
    } catch (UserNotFoundException e) {
      throw new InvalidCredentialsException();
    }

    if (!user.getActive()) {
      throw new UserInactiveException(user.getId());
    }

    if (!passwordEncoder.matches(command.password(), user.getPassword())) {
      throw new InvalidCredentialsException();
    }

    String token = tokenProvider.generateToken(user);
    return token;
  }
}
