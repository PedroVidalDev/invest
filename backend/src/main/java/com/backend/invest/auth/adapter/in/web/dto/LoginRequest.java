package com.backend.invest.auth.adapter.in.web.dto;

import com.backend.invest.auth.domain.port.in.command.LoginCommand;

public record LoginRequest(String email, String password) {
  public LoginCommand toCommand() {
    return new LoginCommand(email, password);
  }
}
