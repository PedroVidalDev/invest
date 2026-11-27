package com.backend.invest.auth.domain.port.in;

import com.backend.invest.auth.domain.port.in.command.LoginCommand;

public interface LoginUseCase {
  String login(LoginCommand command);
}
